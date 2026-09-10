//package lk.ijse.boat_reservation.service.impl;
//
//import lk.ijse.boat_reservation.dto.MaintenanceLogDTO;
//import lk.ijse.boat_reservation.entity.Boat;
//import lk.ijse.boat_reservation.entity.MaintenanceLog;
//import lk.ijse.boat_reservation.exception.CustomException;
//import lk.ijse.boat_reservation.repository.BoatRepository;
//import lk.ijse.boat_reservation.repository.MaintenanceLogRepository;
//import lk.ijse.boat_reservation.service.MaintenanceLogService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Slf4j
//public class MaintenanceLogServiceImpl implements MaintenanceLogService {
//
//    private final MaintenanceLogRepository maintenanceLogRepository;
//    private final BoatRepository boatRepository;
//
//    public MaintenanceLogServiceImpl(
//            MaintenanceLogRepository maintenanceLogRepository,
//            BoatRepository boatRepository) {
//
//        this.maintenanceLogRepository = maintenanceLogRepository;
//        this.boatRepository = boatRepository;
//    }
//
//    @Override
//    @Transactional
//    public MaintenanceLogDTO createLog(MaintenanceLogDTO dto) {
//        log.info("Execute method createLog()");
//
//        try {
//            if (dto == null) {
//                throw new CustomException(
//                        400,
//                        "Maintenance log data cannot be null!"
//                );
//            }
//
//            if (dto.getBoatId() == null ||
//                    dto.getBoatId() <= 0) {
//
//                throw new CustomException(
//                        400,
//                        "Valid Boat ID is required!"
//                );
//            }
//
//            Optional<Boat> boatOptional =
//                    boatRepository.findById(dto.getBoatId());
//
//            if (boatOptional.isEmpty()) {
//                log.error(
//                        "Boat with id {} does not exist",
//                        dto.getBoatId()
//                );
//
//                throw new CustomException(
//                        404,
//                        "Boat not found with id: "
//                                + dto.getBoatId()
//                );
//            }
//
//            Boat boat = boatOptional.get();
//
//            MaintenanceLog logEntity =
//                    new MaintenanceLog();
//
//            logEntity.setServiceDate(
//                    dto.getServiceDate()
//            );
//            logEntity.setDescription(
//                    dto.getDescription()
//            );
//            logEntity.setCost(
//                    dto.getCost()
//            );
//            logEntity.setStatus(
//                    dto.getStatus()
//            );
//            logEntity.setBoat(boat);
//
//            MaintenanceLog savedLog =
//                    maintenanceLogRepository.save(logEntity);
//
//            MaintenanceLogDTO responseDTO =
//                    new MaintenanceLogDTO();
//
//            responseDTO.setLogId(
//                    savedLog.getLogId()
//            );
//            responseDTO.setServiceDate(
//                    savedLog.getServiceDate()
//            );
//            responseDTO.setDescription(
//                    savedLog.getDescription()
//            );
//            responseDTO.setCost(
//                    savedLog.getCost()
//            );
//            responseDTO.setStatus(
//                    savedLog.getStatus()
//            );
//
//            if (savedLog.getBoat() != null) {
//                responseDTO.setBoatId(
//                        savedLog.getBoat().getBoatId()
//                );
//                responseDTO.setBoatName(
//                        savedLog.getBoat().getBoatName()
//                );
//            }
//
//            log.info("Maintenance log saved successfully");
//
//            return responseDTO;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while creating maintenance log: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional
//    public MaintenanceLogDTO updateLog(
//            Long id,
//            MaintenanceLogDTO dto) {
//
//        log.info("Execute method updateLog()");
//
//        try {
//            if (id == null || id <= 0) {
//                throw new CustomException(
//                        400,
//                        "Valid Maintenance Log ID is required!"
//                );
//            }
//
//            if (dto == null) {
//                throw new CustomException(
//                        400,
//                        "Maintenance log data cannot be null!"
//                );
//            }
//
//            Optional<MaintenanceLog> optionalLog =
//                    maintenanceLogRepository.findById(id);
//
//            if (optionalLog.isEmpty()) {
//                log.error(
//                        "Maintenance log with id {} does not exist",
//                        id
//                );
//
//                throw new CustomException(
//                        404,
//                        "Maintenance log not found with id: " + id
//                );
//            }
//
//            MaintenanceLog logEntity =
//                    optionalLog.get();
//
//            logEntity.setServiceDate(
//                    dto.getServiceDate()
//            );
//            logEntity.setDescription(
//                    dto.getDescription()
//            );
//            logEntity.setCost(
//                    dto.getCost()
//            );
//            logEntity.setStatus(
//                    dto.getStatus()
//            );
//
//            MaintenanceLog updatedLog =
//                    maintenanceLogRepository.save(logEntity);
//
//            MaintenanceLogDTO responseDTO =
//                    new MaintenanceLogDTO();
//
//            responseDTO.setLogId(
//                    updatedLog.getLogId()
//            );
//            responseDTO.setServiceDate(
//                    updatedLog.getServiceDate()
//            );
//            responseDTO.setDescription(
//                    updatedLog.getDescription()
//            );
//            responseDTO.setCost(
//                    updatedLog.getCost()
//            );
//            responseDTO.setStatus(
//                    updatedLog.getStatus()
//            );
//
//            if (updatedLog.getBoat() != null) {
//                responseDTO.setBoatId(
//                        updatedLog.getBoat().getBoatId()
//                );
//                responseDTO.setBoatName(
//                        updatedLog.getBoat().getBoatName()
//                );
//            }
//
//            log.info("Maintenance log updated successfully");
//
//            return responseDTO;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while updating maintenance log: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public MaintenanceLogDTO getLogById(Long id) {
//        log.info("Execute method getLogById()");
//
//        try {
//            if (id == null || id <= 0) {
//                throw new CustomException(
//                        400,
//                        "Invalid Maintenance Log ID: " + id
//                );
//            }
//
//            Optional<MaintenanceLog> optionalLog =
//                    maintenanceLogRepository.findById(id);
//
//            if (optionalLog.isEmpty()) {
//                log.error(
//                        "Maintenance log with id {} does not exist",
//                        id
//                );
//
//                throw new CustomException(
//                        404,
//                        "Maintenance log not found with id: " + id
//                );
//            }
//
//            MaintenanceLog logEntity =
//                    optionalLog.get();
//
//            MaintenanceLogDTO dto =
//                    new MaintenanceLogDTO();
//
//            dto.setLogId(
//                    logEntity.getLogId()
//            );
//            dto.setServiceDate(
//                    logEntity.getServiceDate()
//            );
//            dto.setDescription(
//                    logEntity.getDescription()
//            );
//            dto.setCost(
//                    logEntity.getCost()
//            );
//            dto.setStatus(
//                    logEntity.getStatus()
//            );
//
//            if (logEntity.getBoat() != null) {
//                dto.setBoatId(
//                        logEntity.getBoat().getBoatId()
//                );
//                dto.setBoatName(
//                        logEntity.getBoat().getBoatName()
//                );
//            }
//
//            return dto;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while fetching maintenance log: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<MaintenanceLogDTO> getLogsByBoat(
//            Long boatId) {
//
//        log.info("Execute method getLogsByBoat()");
//
//        try {
//            if (boatId == null || boatId <= 0) {
//                throw new CustomException(
//                        400,
//                        "Invalid Boat ID: " + boatId
//                );
//            }
//
//            List<MaintenanceLog> logs =
//                    maintenanceLogRepository
//                            .findByBoatBoatId(boatId);
//
//            List<MaintenanceLogDTO> responseList =
//                    new ArrayList<>();
//
//            for (MaintenanceLog logEntity : logs) {
//
//                MaintenanceLogDTO dto =
//                        new MaintenanceLogDTO();
//
//                dto.setLogId(
//                        logEntity.getLogId()
//                );
//                dto.setServiceDate(
//                        logEntity.getServiceDate()
//                );
//                dto.setDescription(
//                        logEntity.getDescription()
//                );
//                dto.setCost(
//                        logEntity.getCost()
//                );
//                dto.setStatus(
//                        logEntity.getStatus()
//                );
//
//                if (logEntity.getBoat() != null) {
//                    dto.setBoatId(
//                            logEntity.getBoat().getBoatId()
//                    );
//                    dto.setBoatName(
//                            logEntity.getBoat().getBoatName()
//                    );
//                }
//
//                responseList.add(dto);
//            }
//
//            return responseList;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while fetching maintenance logs by boat: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<MaintenanceLogDTO> getAllLogs() {
//        log.info("Execute method getAllLogs()");
//
//        try {
//            List<MaintenanceLog> logs =
//                    maintenanceLogRepository.findAll();
//
//            List<MaintenanceLogDTO> responseList =
//                    new ArrayList<>();
//
//            for (MaintenanceLog logEntity : logs) {
//
//                MaintenanceLogDTO dto =
//                        new MaintenanceLogDTO();
//
//                dto.setLogId(
//                        logEntity.getLogId()
//                );
//                dto.setServiceDate(
//                        logEntity.getServiceDate()
//                );
//                dto.setDescription(
//                        logEntity.getDescription()
//                );
//                dto.setCost(
//                        logEntity.getCost()
//                );
//                dto.setStatus(
//                        logEntity.getStatus()
//                );
//
//                if (logEntity.getBoat() != null) {
//                    dto.setBoatId(
//                            logEntity.getBoat().getBoatId()
//                    );
//                    dto.setBoatName(
//                            logEntity.getBoat().getBoatName()
//                    );
//                }
//
//                responseList.add(dto);
//            }
//
//            return responseList;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while fetching all maintenance logs: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional
//    public void deleteLog(Long id) {
//        log.info("Execute method deleteLog()");
//
//        try {
//            if (id == null || id <= 0) {
//                throw new CustomException(
//                        400,
//                        "Invalid Maintenance Log ID: " + id
//                );
//            }
//
//            Optional<MaintenanceLog> optionalLog =
//                    maintenanceLogRepository.findById(id);
//
//            if (optionalLog.isEmpty()) {
//                log.error(
//                        "Maintenance log with id {} does not exist",
//                        id
//                );
//
//                throw new CustomException(
//                        404,
//                        "Maintenance log not found with id: " + id
//                );
//            }
//
//            maintenanceLogRepository.deleteById(id);
//
//            log.info("Maintenance log deleted successfully");
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while deleting maintenance log: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }

//    }
//}

package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.MaintenanceLogDTO;
import lk.ijse.boat_reservation.entity.Boat;
import lk.ijse.boat_reservation.entity.MaintenanceLog;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.BoatRepository;
import lk.ijse.boat_reservation.repository.MaintenanceLogRepository;
import lk.ijse.boat_reservation.service.MaintenanceLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MaintenanceLogServiceImpl implements MaintenanceLogService {

    private final MaintenanceLogRepository maintenanceLogRepository;
    private final BoatRepository boatRepository;

    public MaintenanceLogServiceImpl(
            MaintenanceLogRepository maintenanceLogRepository,
            BoatRepository boatRepository) {
        this.maintenanceLogRepository = maintenanceLogRepository;
        this.boatRepository = boatRepository;
    }

    @Override
    @Transactional
    public MaintenanceLogDTO createLog(MaintenanceLogDTO dto) {
        log.info("Execute method createLog()");

        try {
            if (dto == null) {
                throw new CustomException(400, "Maintenance log data cannot be null!");
            }

            if (dto.getBoatId() == null || dto.getBoatId() <= 0) {
                throw new CustomException(400, "Valid Boat ID is required!");
            }

            Optional<Boat> boatOptional = boatRepository.findById(dto.getBoatId());

            if (boatOptional.isEmpty()) {
                log.error("Boat with id {} does not exist", dto.getBoatId());
                throw new CustomException(404, "Boat not found with id: " + dto.getBoatId());
            }

            Boat boat = boatOptional.get();

            MaintenanceLog logEntity = new MaintenanceLog();
            logEntity.setStartDate(dto.getStartDate());
            logEntity.setEndDate(dto.getEndDate());
            logEntity.setDescription(dto.getDescription());
            logEntity.setCost(dto.getCost());
            logEntity.setStatus(dto.getStatus());
            logEntity.setBoat(boat);

            MaintenanceLog savedLog = maintenanceLogRepository.save(logEntity);

            MaintenanceLogDTO responseDTO = new MaintenanceLogDTO();
            responseDTO.setLogId(savedLog.getLogId());
            responseDTO.setStartDate(savedLog.getStartDate());
            responseDTO.setEndDate(savedLog.getEndDate());
            responseDTO.setDescription(savedLog.getDescription());
            responseDTO.setCost(savedLog.getCost());
            responseDTO.setStatus(savedLog.getStatus());

            if (savedLog.getBoat() != null) {
                responseDTO.setBoatId(savedLog.getBoat().getBoatId());
                responseDTO.setBoatName(savedLog.getBoat().getBoatName());
            }

            log.info("Maintenance log saved successfully");
            return responseDTO;

        } catch (Exception e) {
            log.error("Error occurred while creating maintenance log: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public MaintenanceLogDTO updateLog(Long id, MaintenanceLogDTO dto) {
        log.info("Execute method updateLog()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(400, "Valid Maintenance Log ID is required!");
            }

            if (dto == null) {
                throw new CustomException(400, "Maintenance log data cannot be null!");
            }

            Optional<MaintenanceLog> optionalLog = maintenanceLogRepository.findById(id);

            if (optionalLog.isEmpty()) {
                log.error("Maintenance log with id {} does not exist", id);
                throw new CustomException(404, "Maintenance log not found with id: " + id);
            }

            MaintenanceLog logEntity = optionalLog.get();
            logEntity.setStartDate(dto.getStartDate());
            logEntity.setEndDate(dto.getEndDate());
            logEntity.setDescription(dto.getDescription());
            logEntity.setCost(dto.getCost());
            logEntity.setStatus(dto.getStatus());

            MaintenanceLog updatedLog = maintenanceLogRepository.save(logEntity);

            MaintenanceLogDTO responseDTO = new MaintenanceLogDTO();
            responseDTO.setLogId(updatedLog.getLogId());
            responseDTO.setStartDate(updatedLog.getStartDate());
            responseDTO.setEndDate(updatedLog.getEndDate());
            responseDTO.setDescription(updatedLog.getDescription());
            responseDTO.setCost(updatedLog.getCost());
            responseDTO.setStatus(updatedLog.getStatus());

            if (updatedLog.getBoat() != null) {
                responseDTO.setBoatId(updatedLog.getBoat().getBoatId());
                responseDTO.setBoatName(updatedLog.getBoat().getBoatName());
            }

            log.info("Maintenance log updated successfully");
            return responseDTO;

        } catch (Exception e) {
            log.error("Error occurred while updating maintenance log: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MaintenanceLogDTO getLogById(Long id) {
        log.info("Execute method getLogById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(400, "Invalid Maintenance Log ID: " + id);
            }

            Optional<MaintenanceLog> optionalLog = maintenanceLogRepository.findById(id);

            if (optionalLog.isEmpty()) {
                log.error("Maintenance log with id {} does not exist", id);
                throw new CustomException(404, "Maintenance log not found with id: " + id);
            }

            MaintenanceLog logEntity = optionalLog.get();

            MaintenanceLogDTO dto = new MaintenanceLogDTO();
            dto.setLogId(logEntity.getLogId());
            dto.setStartDate(logEntity.getStartDate());
            dto.setEndDate(logEntity.getEndDate());
            dto.setDescription(logEntity.getDescription());
            dto.setCost(logEntity.getCost());
            dto.setStatus(logEntity.getStatus());

            if (logEntity.getBoat() != null) {
                dto.setBoatId(logEntity.getBoat().getBoatId());
                dto.setBoatName(logEntity.getBoat().getBoatName());
            }

            return dto;

        } catch (Exception e) {
            log.error("Error occurred while fetching maintenance log: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceLogDTO> getLogsByBoat(Long boatId) {
        log.info("Execute method getLogsByBoat()");

        try {
            if (boatId == null || boatId <= 0) {
                throw new CustomException(400, "Invalid Boat ID: " + boatId);
            }

            List<MaintenanceLog> logs = maintenanceLogRepository.findByBoatBoatId(boatId);
            List<MaintenanceLogDTO> responseList = new ArrayList<>();

            for (MaintenanceLog logEntity : logs) {
                MaintenanceLogDTO dto = new MaintenanceLogDTO();
                dto.setLogId(logEntity.getLogId());
                dto.setStartDate(logEntity.getStartDate());
                dto.setEndDate(logEntity.getEndDate());
                dto.setDescription(logEntity.getDescription());
                dto.setCost(logEntity.getCost());
                dto.setStatus(logEntity.getStatus());

                if (logEntity.getBoat() != null) {
                    dto.setBoatId(logEntity.getBoat().getBoatId());
                    dto.setBoatName(logEntity.getBoat().getBoatName());
                }

                responseList.add(dto);
            }

            return responseList;

        } catch (Exception e) {
            log.error("Error occurred while fetching maintenance logs by boat: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceLogDTO> getAllLogs() {
        log.info("Execute method getAllLogs()");

        try {
            List<MaintenanceLog> logs = maintenanceLogRepository.findAll();
            List<MaintenanceLogDTO> responseList = new ArrayList<>();

            for (MaintenanceLog logEntity : logs) {
                MaintenanceLogDTO dto = new MaintenanceLogDTO();
                dto.setLogId(logEntity.getLogId());
                dto.setStartDate(logEntity.getStartDate());
                dto.setEndDate(logEntity.getEndDate());
                dto.setDescription(logEntity.getDescription());
                dto.setCost(logEntity.getCost());
                dto.setStatus(logEntity.getStatus());

                if (logEntity.getBoat() != null) {
                    dto.setBoatId(logEntity.getBoat().getBoatId());
                    dto.setBoatName(logEntity.getBoat().getBoatName());
                }

                responseList.add(dto);
            }

            return responseList;

        } catch (Exception e) {
            log.error("Error occurred while fetching all maintenance logs: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteLog(Long id) {
        log.info("Execute method deleteLog()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(400, "Invalid Maintenance Log ID: " + id);
            }

            Optional<MaintenanceLog> optionalLog = maintenanceLogRepository.findById(id);

            if (optionalLog.isEmpty()) {
                log.error("Maintenance log with id {} does not exist", id);
                throw new CustomException(404, "Maintenance log not found with id: " + id);
            }

            maintenanceLogRepository.deleteById(id);
            log.info("Maintenance log deleted successfully");

        } catch (Exception e) {
            log.error("Error occurred while deleting maintenance log: {}", e.getMessage());
            throw e;
        }
    }
}