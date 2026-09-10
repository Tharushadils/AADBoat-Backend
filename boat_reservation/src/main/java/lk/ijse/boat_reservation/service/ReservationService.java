package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.ReservationDTO;
import java.util.List;

public interface ReservationService {

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    ReservationDTO updateReservation(ReservationDTO reservationDTO);

    void cancelReservation(long reservationId);

    ReservationDTO getReservationById(long reservationId);

    List<ReservationDTO> getAllReservations();
}