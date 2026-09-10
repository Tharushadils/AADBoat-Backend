package lk.ijse.boat_reservation.repository;

import lk.ijse.boat_reservation.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE slots SET no_of_seats = :noOfSeats, status = :status WHERE slot_id = :slotId", nativeQuery = true)
    int updateSlotSeatsAndStatus(@Param("noOfSeats") int noOfSeats,
                                 @Param("status") String status,
                                 @Param("slotId") Long slotId);

    List<Slot> findByDateAndStatus(LocalDate date, String status);
}