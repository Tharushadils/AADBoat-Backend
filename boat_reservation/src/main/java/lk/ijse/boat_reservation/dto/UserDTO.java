package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long userId;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String contactNumber;
    private List<String> roles;
}