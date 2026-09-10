package lk.ijse.boat_reservation.repository;

import lk.ijse.boat_reservation.entity.AddOnService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddOnServiceRepository extends JpaRepository<AddOnService, Long> {
}