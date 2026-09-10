package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.UserDTO;
import lk.ijse.boat_reservation.entity.User;
import lk.ijse.boat_reservation.enumeration.UserRole;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.UserRepository;
import lk.ijse.boat_reservation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lk.ijse.boat_reservation.enumeration.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO registerUser(UserDTO dto) {
        log.info("Execute method registerUser()");

        try {
            if (dto == null) {
                throw new CustomException(
                        400,
                        "User data cannot be null!"
                );
            }

            if (dto.getUsername() == null ||
                    dto.getUsername().trim().isEmpty()) {

                throw new CustomException(
                        400,
                        "Username is required!"
                );
            }

            if (dto.getEmail() == null ||
                    dto.getEmail().trim().isEmpty()) {

                throw new CustomException(
                        400,
                        "Email is required!"
                );
            }

            if (dto.getPassword() == null ||
                    dto.getPassword().trim().isEmpty()) {

                throw new CustomException(
                        400,
                        "Password is required!"
                );
            }

            if (userRepository.existsByUsername(
                    dto.getUsername())) {

                log.error(
                        "Username already exists: {}",
                        dto.getUsername()
                );

                throw new CustomException(
                        409,
                        "Username already exists: "
                                + dto.getUsername()
                );
            }

            if (userRepository.existsByEmail(
                    dto.getEmail())) {

                log.error(
                        "Email already exists: {}",
                        dto.getEmail()
                );

                throw new CustomException(
                        409,
                        "Email already exists: "
                                + dto.getEmail()
                );
            }

            User user = new User();

            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            user.setEmail(dto.getEmail());
            user.setFullName(dto.getFullName());
            user.setContactNumber(dto.getContactNumber());

            User savedUser =
                    userRepository.save(user);

            log.info("User registered successfully");

            return convertToDTO(savedUser);

        } catch (Exception e) {
            log.error(
                    "Error occurred while registering user: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public UserDTO updateUser(
            Long id,
            UserDTO dto) {

        log.info("Execute method updateUser()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid User ID: " + id
                );
            }

            if (dto == null) {
                throw new CustomException(
                        400,
                        "User data cannot be null!"
                );
            }

            Optional<User> userOptional =
                    userRepository.findById(id);

            if (userOptional.isEmpty()) {
                log.error(
                        "User with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "User not found with id: " + id
                );
            }

            User user = userOptional.get();

            user.setEmail(dto.getEmail());
            user.setFullName(dto.getFullName());
            user.setContactNumber(
                    dto.getContactNumber()
            );

            if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
                user.setRole(dto.getRoles().get(0));
            }

            User updatedUser =
                    userRepository.save(user);

            log.info(
                    "User updated successfully with id: {}",
                    id
            );

            return convertToDTO(updatedUser);

        } catch (Exception e) {
            log.error(
                    "Error occurred while updating user: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        log.info("Execute method getUserById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid User ID: " + id
                );
            }

            Optional<User> userOptional =
                    userRepository.findById(id);

            if (userOptional.isEmpty()) {
                log.error(
                        "User with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "User not found with id: " + id
                );
            }

            return convertToDTO(
                    userOptional.get()
            );

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching user: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByUsername(
            String username) {

        log.info("Execute method getUserByUsername()");

        try {
            if (username == null ||
                    username.trim().isEmpty()) {

                throw new CustomException(
                        400,
                        "Username is required!"
                );
            }

            Optional<User> userOptional =
                    userRepository.findByUsername(username);

            if (userOptional.isEmpty()) {
                log.error(
                        "User with username {} does not exist",
                        username
                );

                throw new CustomException(
                        404,
                        "User not found with username: "
                                + username
                );
            }

            return convertToDTO(
                    userOptional.get()
            );

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching user by username: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        log.info("Execute method getAllUsers()");

        try {
            List<User> users =
                    userRepository.findAll();

            List<UserDTO> responseList =
                    new ArrayList<>();

            for (User user : users) {

                UserDTO dto =
                        convertToDTO(user);

                responseList.add(dto);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching all users: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Execute method deleteUser()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid User ID: " + id
                );
            }

            Optional<User> userOptional =
                    userRepository.findById(id);

            if (userOptional.isEmpty()) {
                log.error(
                        "User with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "User not found with id: " + id
                );
            }

            userRepository.deleteById(id);

            log.info(
                    "User deleted successfully with id: {}",
                    id
            );

        } catch (Exception e) {
            log.error(
                    "Error occurred while deleting user: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    private UserDTO convertToDTO(User user) {

        List<UserRole> roleEnums = user.getRole() != null
                ? List.of(user.getRole())
                : List.of(UserRole.CUSTOMER);

        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                null,
                user.getEmail(),
                user.getFullName(),
                user.getContactNumber(),
                roleEnums
        );
    }
}