package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOnServiceDTO {
    private long serviceId;
    private String serviceName;
    private double price;
    private String description;
}