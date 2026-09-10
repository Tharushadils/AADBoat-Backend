package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.DockDTO;
import java.util.List;

public interface DockService {
    DockDTO saveDock(DockDTO dockDTO);
    DockDTO updateDock(Long id, DockDTO dockDTO);
    DockDTO getDockById(Long id);
    List<DockDTO> getAllDocks();
    void deleteDock(Long id);
}