package lk.ijse.boat_reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maintenance_logs")
public class MaintenanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;

    @Column(nullable = false)
    private LocalDate serviceDate;

    private String description;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private String status;//pending,inprogress,completed,cancel

    @ManyToOne(fetch = FetchType.LAZY)
    private Boat boat;
}