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
@Table(name = "docks")
public class Dock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dockId;

    @Column(nullable = false)
    private String dockName;

    @Column(nullable = false)
    private String locationAddress;

    @Column(nullable = false)
    private int maxCapacity;

    @OneToMany(mappedBy = "dock", cascade = CascadeType.ALL)
    private List<Boat> boatList;
}