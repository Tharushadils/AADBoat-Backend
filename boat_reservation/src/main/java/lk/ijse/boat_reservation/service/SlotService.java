package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.SlotDTO;
import java.time.LocalDate;
import java.util.List;

public interface SlotService {
    SlotDTO saveSlot(SlotDTO dto);
    SlotDTO updateSlot(Long id, SlotDTO dto);
    SlotDTO getSlotById(Long id);
    List<SlotDTO> getAllSlots();
    List<SlotDTO> getAvailableSlotsByDate(LocalDate date);
    void deleteSlot(Long id);
}