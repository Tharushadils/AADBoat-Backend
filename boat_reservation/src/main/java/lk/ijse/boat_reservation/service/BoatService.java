package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.BoatDTO;
import java.util.List;

public interface BoatService {
    BoatDTO saveBoat(BoatDTO boatDTO);
    BoatDTO updateBoat(Long id, BoatDTO boatDTO);
    BoatDTO getBoatById(Long id);
    List<BoatDTO> getAllBoats();
    List<BoatDTO> getBoatsByDock(Long dockId);
    List<BoatDTO> getBoatsByCategory(Long categoryId);
    void deleteBoat(Long id);
}