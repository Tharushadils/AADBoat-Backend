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
@Table(name = "boat_categories")//naming tha table
public class  BoatCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String categoryName; // e.g.(veritees) Speedboat, Yacht, Catamaran ,Kayak

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Boat> boats;
}