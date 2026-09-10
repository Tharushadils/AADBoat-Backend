package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationItemDTO {
    private Long id;
    private int quantity;
    private double unitPrice;
    private Long addOnServiceId;
    private String serviceName;
    private Long reservationId;
}