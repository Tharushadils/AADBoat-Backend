package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long reservationId;

    private String status;

    private LocalDate reservationDate;

    // Default value eka 1 widihata assign kera
    private Integer noOfSeats = 1;

    private Long userId;
    private String userName;

    private Long boatId;
    private String boatName;

    private Long slotId;
    private String slotTime;
    private String slotDate;

    private Long startDockId;
    private String startDockName;

    private Long endDockId;
    private String endDockName;

    private List<Long> addOnServiceIds;
    private List<AddOnServiceDTO> addOnServices;

    // Lombok eka override karalama explicit custom setter ekak demma
    // Front-end eken "noOfSeats": null yawwoth direct null nova 1 wenne meken
    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = (noOfSeats != null) ? noOfSeats : 1;
    }
}