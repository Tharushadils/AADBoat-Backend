package lk.ijse.boat_reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "slots")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Long slotId;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "no_of_seats", nullable = false)
    private Integer noOfSeats;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "slot")
    @lombok.ToString.Exclude//StackOverflowError nawaththnw
    @lombok.EqualsAndHashCode.Exclude//unwanted comparison problems nawaththnw
    private List<Reservation> reservations;
}