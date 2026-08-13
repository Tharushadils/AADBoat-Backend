package lk.ijse.boat_reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;

    @Column(nullable = false, unique = true)
    private String reservationCode;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Boat boat;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationItem> reservationItems;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Invoice invoice;
}