package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.MaintenanceLogDTO;
import java.util.List;

public interface MaintenanceLogService {
    MaintenanceLogDTO createLog(MaintenanceLogDTO dto);
    MaintenanceLogDTO updateLog(Long id, MaintenanceLogDTO dto);
    MaintenanceLogDTO getLogById(Long id);
    List<MaintenanceLogDTO> getLogsByBoat(Long boatId);
    List<MaintenanceLogDTO> getAllLogs();
    void deleteLog(Long id);
}