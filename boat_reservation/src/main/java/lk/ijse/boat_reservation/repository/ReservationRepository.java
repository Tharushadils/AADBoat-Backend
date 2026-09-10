package lk.ijse.boat_reservation.repository;

import lk.ijse.boat_reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUserId(Long userId);
    List<Reservation> findByBoatBoatId(Long boatId);
    List<Reservation> findByStartDockDockId(Long startDockId);
    List<Reservation> findByEndDockDockId(Long endDockId);
}