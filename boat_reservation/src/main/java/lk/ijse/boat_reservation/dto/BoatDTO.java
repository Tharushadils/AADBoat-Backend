package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoatDTO {
    private long boatId;
    private String boatName;
    private int passengerCapacity;
    private double baseHourlyRate;
    private String status;
    private Long dockId;
    private String dockName;
    private Long categoryId;
    private String categoryName;
}