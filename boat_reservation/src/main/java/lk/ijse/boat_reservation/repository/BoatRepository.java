package lk.ijse.boat_reservation.repository;

import lk.ijse.boat_reservation.entity.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {

    List<Boat> findByDock_DockId(Long dockId);

    List<Boat> findByCategory_CategoryId(Long categoryId);

    List<Boat> findByStatus(String status);
}