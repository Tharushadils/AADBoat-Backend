package lk.ijse.boat_reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long routeId;

    @Column(nullable = false)
    private String routeName;

    @Column(nullable = false)
    private String startPoint;

    @Column(nullable = false)
    private String destinationPoint;

    @Column(nullable = false)
    private double estimatedDurationHours;

    @Column(nullable = false)
    private double baseRouteFee;

    @OneToMany(mappedBy = "route")
    private List<Reservation> reservations;
}