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
@Table(name = "boats")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boatId;

    @Column(nullable = false)
    private String boatName;

    @Column(nullable = false)
    private int passengerCapacity;

    @Column(nullable = false)
    private double baseHourlyRate;


    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dock dock;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoatCategory category;

    @OneToMany(mappedBy = "boat", cascade = CascadeType.ALL)
    private List<MaintenanceLog> maintenanceLogs;

    @OneToMany(mappedBy = "boat")
    private List<Reservation> reservations;
}