package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.AddOnServiceDTO;
import lk.ijse.boat_reservation.dto.ReservationDTO;
import lk.ijse.boat_reservation.entity.*;
import lk.ijse.boat_reservation.repository.*;
import lk.ijse.boat_reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SlotRepository slotRepository;
    private final UserRepository userRepository;
    private final BoatRepository boatRepository;
    private final DockRepository dockRepository;
    private final AddOnServiceRepository addOnServiceRepository;



    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ReservationDTO createReservation(ReservationDTO dto) {


        Slot slot = slotRepository.findById(dto.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found with ID: " + dto.getSlotId()));


        if (slot.getNoOfSeats() < dto.getNoOfSeats()) {
            throw new RuntimeException("Not enough seats available! Available seats: " + slot.getNoOfSeats());
        }


        int newSeatCount = slot.getNoOfSeats() - dto.getNoOfSeats();
        String newStatus = (newSeatCount == 0) ? "FULL" : slot.getStatus();


        int rowsAffected = slotRepository.updateSlotSeatsAndStatus(newSeatCount, newStatus, slot.getSlotId());

        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to update slot seats in DB.");
        }


        slot.setNoOfSeats(newSeatCount);
        slot.setStatus(newStatus);


        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Boat boat = boatRepository.findById(dto.getBoatId())
                .orElseThrow(() -> new RuntimeException("Boat not found"));
        Dock startDock = dockRepository.findById(dto.getStartDockId())
                .orElseThrow(() -> new RuntimeException("Start Dock not found"));
        Dock endDock = dockRepository.findById(dto.getEndDockId())
                .orElseThrow(() -> new RuntimeException("End Dock not found"));

        Reservation reservation = new Reservation();
        reservation.setReservationDate(dto.getReservationDate() != null ? dto.getReservationDate() : LocalDate.now());
        reservation.setStatus(dto.getStatus() != null ? dto.getStatus() : "CONFIRMED");
        reservation.setNoOfSeats(dto.getNoOfSeats());
        reservation.setUser(user);
        reservation.setBoat(boat);
        reservation.setSlot(slot);
        reservation.setStartDock(startDock);
        reservation.setEndDock(endDock);

        Reservation savedReservation = reservationRepository.save(reservation);

        return convertToDTO(savedReservation);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ReservationDTO updateReservation(ReservationDTO dto) {

        Reservation existingReservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new RuntimeException("Sorry, Reservation not found with ID: " + dto.getReservationId()));

        Slot slot = existingReservation.getSlot();


        if (dto.getNoOfSeats() != null && !dto.getNoOfSeats().equals(existingReservation.getNoOfSeats())) {
            int diff = dto.getNoOfSeats() - existingReservation.getNoOfSeats();

            if (slot.getNoOfSeats() < diff) {
                throw new RuntimeException("Sorry, Not enough seats available to expand reservation.");
            }

            int newSeatCount = slot.getNoOfSeats() - diff;
            slot.setNoOfSeats(newSeatCount);
            slot.setStatus(newSeatCount == 0 ? "FULL" : "AVAILABLE");
            slotRepository.save(slot);

            existingReservation.setNoOfSeats(dto.getNoOfSeats());
        }

        if (dto.getStatus() != null) {
            existingReservation.setStatus(dto.getStatus());
        }

        Reservation updated = reservationRepository.save(existingReservation);
        return convertToDTO(updated);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void cancelReservation(long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Sorry, Reservation not found with ID: " + reservationId));


        Slot slot = reservation.getSlot();
        slot.setNoOfSeats(slot.getNoOfSeats() + reservation.getNoOfSeats());
        if ("FULL".equalsIgnoreCase(slot.getStatus())) {
            slot.setStatus("AVAILABLE");
        }
        slotRepository.save(slot);


        reservationRepository.delete(reservation);
    }


    @Override
    @Transactional(readOnly = true)
    public ReservationDTO getReservationById(long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Sorry, Reservation not found with ID: " + reservationId));
        return convertToDTO(reservation);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private ReservationDTO convertToDTO(Reservation entity) {
        ReservationDTO dto = new ReservationDTO();
        dto.setReservationId(entity.getReservationId());
        dto.setStatus(entity.getStatus());
        dto.setReservationDate(entity.getReservationDate());
        dto.setNoOfSeats(entity.getNoOfSeats());

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getUserId());
            dto.setUserName(entity.getUser().getUsername());
        }

        if (entity.getBoat() != null) {
            dto.setBoatId(entity.getBoat().getBoatId());
            dto.setBoatName(entity.getBoat().getBoatName());
        }

        if (entity.getSlot() != null) {
            dto.setSlotId(entity.getSlot().getSlotId());
            dto.setSlotTime(entity.getSlot().getTime().toString());
            dto.setSlotDate(entity.getSlot().getDate().toString());
        }

        if (entity.getStartDock() != null) {
            dto.setStartDockId(entity.getStartDock().getDockId());
            dto.setStartDockName(entity.getStartDock().getDockName());
        }

        if (entity.getEndDock() != null) {
            dto.setEndDockId(entity.getEndDock().getDockId());
            dto.setEndDockName(entity.getEndDock().getDockName());
        }

        if (entity.getAddOnServices() != null) {
            List<AddOnServiceDTO> addOns = entity.getAddOnServices().stream()
                    .map(service -> new AddOnServiceDTO(service.getServiceId(), service.getServiceName(), service.getPrice()))
                    .collect(Collectors.toList());
            dto.setAddOnServices(addOns);
        }

        return dto;
    }
}