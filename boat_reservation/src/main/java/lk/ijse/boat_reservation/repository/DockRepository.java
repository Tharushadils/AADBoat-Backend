package lk.ijse.boat_reservation.repository;

import lk.ijse.boat_reservation.entity.Dock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DockRepository extends JpaRepository<Dock, Long> {
}