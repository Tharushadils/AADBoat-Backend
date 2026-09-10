//package lk.ijse.boat_reservation.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "reservation_items")
//public class ReservationItem {//general items eg:life jackets
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false)
//    private int quantity;
//
//    @Column(nullable = false)
//    private double unitPrice;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Reservation reservation;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private AddOnService addOnService;
//}