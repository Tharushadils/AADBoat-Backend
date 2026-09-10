package lk.ijse.boat_reservation.entity;

import jakarta.persistence.*;
import lk.ijse.boat_reservation.enumeration.AddOnServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "add_on_services")
public class AddOnService {//extra services order by guests
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(nullable = false)
    private String serviceName; // e.g., Captain, Catering, Photography

    @Column(nullable = false)
    private Double price;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddOnServiceStatus status;
}