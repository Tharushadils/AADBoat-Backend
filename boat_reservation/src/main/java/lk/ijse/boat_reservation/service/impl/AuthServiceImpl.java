package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.AuthDTO;
import lk.ijse.boat_reservation.dto.UserDTO;
import lk.ijse.boat_reservation.entity.User;
import lk.ijse.boat_reservation.enumeration.UserRole;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.UserRepository;
import lk.ijse.boat_reservation.security.JwtUtil;
import lk.ijse.boat_reservation.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();

    @Override
    @Transactional(readOnly = true)
    public AuthDTO.AuthResponse login(AuthDTO.LoginRequest loginRequest) {
        log.info("Execute method login()");

        if (loginRequest == null) {
            throw new CustomException(400, "Login data cannot be null!");
        }
        if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()) {
            throw new CustomException(400, "Username is required!");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            throw new CustomException(400, "Password is required!");
        }

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new CustomException(404, "User not found with username: " + loginRequest.getUsername()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.error("Invalid credentials for username {}", loginRequest.getUsername());
            throw new CustomException(401, "Invalid credentials!");
        }


        UserRole userRole = user.getRole() != null ? user.getRole() : UserRole.CUSTOMER;
        List<String> roles = Collections.singletonList(userRole.name());


        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(Collections.singletonList(userRole));

        // Generating real JWT token using JwtUtil
        String accessToken = jwtUtil.generateToken(userDTO);
        String refreshToken = jwtUtil.generateToken(userDTO);

        log.info("User logged in successfully");

        return new AuthDTO.AuthResponse(
                accessToken,
                refreshToken,
                user.getUsername(),
                user.getEmail(),
                roles
        );
    }

    @Override
    @Transactional
    public void register(UserDTO userDTO) {
        log.info("Execute method register()");

        if (userDTO == null) {
            throw new CustomException(400, "User data cannot be null!");
        }
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
            throw new CustomException(400, "Username is required!");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            throw new CustomException(400, "Email is required!");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()) {
            throw new CustomException(400, "Password is required!");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException(409, "Email already exists: " + userDTO.getEmail());
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFullName(userDTO.getFullName());
        user.setContactNumber(userDTO.getContactNumber());


        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            user.setRole(userDTO.getRoles().get(0));
        } else {
            user.setRole(UserRole.CUSTOMER); // Default Role
        }

        userRepository.save(user);

        // Generate 6-digit OTP
        String generatedOtp = String.valueOf((int) (Math.random() * 900000) + 100000);
        otpStorage.put(userDTO.getEmail(), generatedOtp);

        log.info("Generated OTP for {}: {}", userDTO.getEmail(), generatedOtp);
    }

    @Override
    @Transactional
    public boolean verifyOtp(String email, String otp) {
        log.info("Execute method verifyOtp()");

        if (email == null || email.trim().isEmpty()) {
            throw new CustomException(400, "Email is required!");
        }
        if (otp == null || otp.trim().isEmpty()) {
            throw new CustomException(400, "OTP is required!");
        }

        String storedOtp = otpStorage.get(email);

        if (storedOtp == null || !storedOtp.equals(otp)) {
            throw new CustomException(400, "Invalid or expired OTP!");
        }

        otpStorage.remove(email);
        log.info("OTP verified successfully");
        return true;
    }
}