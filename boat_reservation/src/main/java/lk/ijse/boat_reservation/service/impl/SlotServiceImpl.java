package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.SlotDTO;
import lk.ijse.boat_reservation.entity.Slot;
import lk.ijse.boat_reservation.enumeration.SlotStatus;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.SlotRepository;
import lk.ijse.boat_reservation.service.SlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;

    public SlotServiceImpl(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SlotDTO saveSlot(SlotDTO slotDTO) {
        log.info("Execute method saveSlot()");

        try {
            if (slotDTO == null) {
                throw new CustomException(400, "Slot data cannot be null!");
            }

            if (slotDTO.getTime() == null) {
                throw new CustomException(400, "Slot time is required!");
            }

            if (slotDTO.getDate() == null) {
                throw new CustomException(400, "Slot date is required!");
            }

            if (slotDTO.getNoOfSeats() == null || slotDTO.getNoOfSeats() <= 0) {
                throw new CustomException(400, "Valid number of seats is required!");
            }

            Slot slot = new Slot();

            slot.setTime(slotDTO.getTime());
            slot.setDate(slotDTO.getDate());
            slot.setNoOfSeats(slotDTO.getNoOfSeats());
            slot.setStatus(SlotStatus.AVAILABLE.name());

            Slot savedSlot = slotRepository.save(slot);

            log.info("Slot saved successfully");

            SlotDTO responseDTO = new SlotDTO();

            responseDTO.setId(savedSlot.getSlotId());
            responseDTO.setTime(savedSlot.getTime());
            responseDTO.setDate(savedSlot.getDate());
            responseDTO.setNoOfSeats(savedSlot.getNoOfSeats());
            responseDTO.setStatus(savedSlot.getStatus());

            log.info("Save Slot Returned ...");

            return responseDTO;

        } catch (Exception e) {
            log.error("Error occurred while saving slot: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SlotDTO updateSlot(Long id, SlotDTO slotDTO) {
        log.info("Execute method updateSlot()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Valid Slot ID is required for update!"
                );
            }

            if (slotDTO == null) {
                throw new CustomException(
                        400,
                        "Slot data cannot be null!"
                );
            }

            Optional<Slot> optionalSlot = slotRepository.findById(id);

            if (optionalSlot.isEmpty()) {
                log.error("Slot with id {} does not exist", id);

                throw new CustomException(
                        404,
                        "Slot not found with id: " + id
                );
            }

            Slot slot = optionalSlot.get();

            if (slotDTO.getTime() != null) {
                slot.setTime(slotDTO.getTime());
            }

            if (slotDTO.getDate() != null) {
                slot.setDate(slotDTO.getDate());
            }

            if (slotDTO.getNoOfSeats() != null && slotDTO.getNoOfSeats() > 0) {
                slot.setNoOfSeats(slotDTO.getNoOfSeats());
            }

            if (slotDTO.getStatus() != null && !slotDTO.getStatus().trim().isEmpty()) {
                slot.setStatus(slotDTO.getStatus());
            }

            Slot updatedSlot = slotRepository.save(slot);

            log.info("Slot updated successfully");

            SlotDTO responseDTO = new SlotDTO();

            responseDTO.setId(updatedSlot.getSlotId());
            responseDTO.setTime(updatedSlot.getTime());
            responseDTO.setDate(updatedSlot.getDate());
            responseDTO.setNoOfSeats(updatedSlot.getNoOfSeats());
            responseDTO.setStatus(updatedSlot.getStatus());

            return responseDTO;

        } catch (Exception e) {
            log.error("Error occurred while updating slot: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SlotDTO getSlotById(Long id) {
        log.info("Execute method getSlotById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Slot ID: " + id
                );
            }

            Optional<Slot> optionalSlot = slotRepository.findById(id);

            if (optionalSlot.isEmpty()) {
                log.error("Slot with id {} does not exist", id);

                throw new CustomException(
                        404,
                        "Slot not found with id: " + id
                );
            }

            Slot slot = optionalSlot.get();

            SlotDTO responseDTO = new SlotDTO();

            responseDTO.setId(slot.getSlotId());
            responseDTO.setTime(slot.getTime());
            responseDTO.setDate(slot.getDate());
            responseDTO.setNoOfSeats(slot.getNoOfSeats());
            responseDTO.setStatus(slot.getStatus());

            return responseDTO;

        } catch (Exception e) {
            log.error("Error occurred while fetching slot: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SlotDTO> getAllSlots() {
        log.info("Execute method getAllSlots()");

        try {
            List<Slot> slots = slotRepository.findAll();

            List<SlotDTO> responseList = new ArrayList<>();

            for (Slot slot : slots) {

                SlotDTO slotDTO = new SlotDTO();

                slotDTO.setId(slot.getSlotId());
                slotDTO.setTime(slot.getTime());
                slotDTO.setDate(slot.getDate());
                slotDTO.setNoOfSeats(slot.getNoOfSeats());
                slotDTO.setStatus(slot.getStatus());

                responseList.add(slotDTO);
            }

            return responseList;

        } catch (Exception e) {
            log.error("Error occurred while fetching all slots: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SlotDTO> getAvailableSlotsByDate(LocalDate date) {
        log.info("Execute method getAvailableSlotsByDate()");

        try {
            if (date == null) {
                throw new CustomException(400, "Date is required!");
            }

            List<Slot> slots = slotRepository.findByDateAndStatus(date, SlotStatus.AVAILABLE.name());

            List<SlotDTO> responseList = new ArrayList<>();

            for (Slot slot : slots) {
                SlotDTO slotDTO = new SlotDTO();

                slotDTO.setId(slot.getSlotId());
                slotDTO.setTime(slot.getTime());
                slotDTO.setDate(slot.getDate());
                slotDTO.setNoOfSeats(slot.getNoOfSeats());
                slotDTO.setStatus(slot.getStatus());

                responseList.add(slotDTO);
            }

            return responseList;

        } catch (Exception e) {
            log.error("Error occurred while fetching available slots: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSlot(Long id) {
        log.info("Execute method deleteSlot()");

        try {
            Optional<Slot> slotOptional = slotRepository.findById(id);

            if (!slotOptional.isPresent()) {

                log.error(
                        "Slot with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Slot not found with id: " + id
                );
            }

            Slot slot = slotOptional.get();

            slot.setStatus(
                    SlotStatus.DELETED.name()
            );

            slotRepository.save(slot);

            log.info(
                    "Slot soft deleted successfully with id: {}",
                    id
            );

        } catch (Exception e) {

            log.error(
                    "Error occurred while deleting slot: {}",
                    e.getMessage()
            );

            throw e;
        }
    }
}