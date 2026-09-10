package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.AuthDTO;
import lk.ijse.boat_reservation.dto.UserDTO;
import lk.ijse.boat_reservation.service.AuthService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse login(@RequestBody AuthDTO.LoginRequest loginRequest) {
        AuthDTO.AuthResponse authResponse = authService.login(loginRequest);
        return new CommonResponse(OPERATION_SUCCESS, authResponse, SUCCESS_MESSAGE);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse register(@RequestBody UserDTO userDTO) {
        authService.register(userDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PostMapping(value = "/verify-otp", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isVerified = authService.verifyOtp(email, otp);
        return new CommonResponse(OPERATION_SUCCESS, isVerified, SUCCESS_MESSAGE);
    }
}