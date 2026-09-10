package lk.ijse.boat_reservation.repository;

import lk.ijse.boat_reservation.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBoatBoatId(Long boatId);
    List<Review> findByUserUserId(Long userId);
}