package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private long paymentId;
    private String transactionId;
    private double amountPaid;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String paymentStatus;
    private Long reservationId;
}