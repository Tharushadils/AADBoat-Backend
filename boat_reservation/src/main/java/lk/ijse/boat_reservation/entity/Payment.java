package lk.ijse.boat_reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private double amountPaid;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String paymentStatus;

    @OneToOne(fetch = FetchType.LAZY)
    private Reservation reservation;
}