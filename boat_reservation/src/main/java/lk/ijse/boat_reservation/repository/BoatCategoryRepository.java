package lk.ijse.boat_reservation.repository;

import lk.ijse.boat_reservation.entity.BoatCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatCategoryRepository extends JpaRepository<BoatCategory, Long> {
}