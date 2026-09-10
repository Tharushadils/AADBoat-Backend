
package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOnServiceDTO {
    private Long serviceId;
    private String serviceName;
    private Double price;
    private String description;
    private String status;

    public AddOnServiceDTO(Long serviceId, String serviceName, Double price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
    }
}