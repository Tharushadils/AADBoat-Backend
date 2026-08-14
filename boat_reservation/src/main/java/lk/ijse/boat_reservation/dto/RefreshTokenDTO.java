package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDTO {
    private long id;
    private String token;
    private Instant expiryDate;
    private long userId;
}