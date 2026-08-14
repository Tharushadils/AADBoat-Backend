package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    private long invoiceId;
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private double totalAmount;
    private long reservationId;
}