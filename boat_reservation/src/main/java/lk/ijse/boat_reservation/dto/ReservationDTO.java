package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private long reservationId;
    private String reservationCode;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double totalPrice;
    private String status;
    private Long userId;
    private String userName;
    private Long boatId;
    private String boatName;
    private Long routeId;
    private String routeName;
    private List<ReservationItemDTO> reservationItems;
}