package lk.ijse.boat_reservation.entity;

import jakarta.persistence.*;
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
    private long serviceId;

    @Column(nullable = false)
    private String serviceName; // e.g., Captain, Catering, Photography

    @Column(nullable = false)
    private double price;

    private String description;
}