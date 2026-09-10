//package lk.ijse.boat_reservation.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lk.ijse.boat_reservation.enumeration.UserRole;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "roles")
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long roleId;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, unique = true)
//    private UserRole roleName; // ADMIN, USER, GUEST
//}