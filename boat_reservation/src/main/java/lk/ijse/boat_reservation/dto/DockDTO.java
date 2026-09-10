package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DockDTO {
    private Long dockId;
    private String dockName;
    private String locationAddress;
    private int maxCapacity;
}