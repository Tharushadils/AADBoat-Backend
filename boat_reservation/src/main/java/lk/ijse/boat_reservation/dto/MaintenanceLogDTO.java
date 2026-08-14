package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLogDTO {
    private long logId;
    private LocalDate serviceDate;
    private String description;
    private double cost;
    private String status;
    private Long boatId;
    private String boatName;
}