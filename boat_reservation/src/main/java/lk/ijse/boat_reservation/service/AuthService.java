
package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.AuthDTO;
import lk.ijse.boat_reservation.dto.UserDTO;

public interface AuthService {
    AuthDTO.AuthResponse login(AuthDTO.LoginRequest loginRequest);
    void register(UserDTO userDTO);
    boolean verifyOtp(String email, String otp);
}