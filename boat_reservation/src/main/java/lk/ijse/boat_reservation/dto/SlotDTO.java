package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {
    private Long id;
    private LocalTime time;
    private LocalDate date;
    private Integer noOfSeats;
    private String status; // e.g., "AVAILABLE", "BOOKED", "CANCELLED"
}