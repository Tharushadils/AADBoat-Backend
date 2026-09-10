package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.DockDTO;
import lk.ijse.boat_reservation.entity.Dock;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.DockRepository;
import lk.ijse.boat_reservation.service.DockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DockServiceImpl implements DockService {

    private final DockRepository dockRepository;

    public DockServiceImpl(DockRepository dockRepository) {
        this.dockRepository = dockRepository;
    }

    @Override
    @Transactional
    public DockDTO saveDock(DockDTO dto) {
        log.info("Execute method saveDock()");

        try {
            if (dto == null) {
                throw new CustomException(
                        400,
                        "Dock data cannot be null!"
                );
            }

            if (dto.getDockName() == null ||
                    dto.getDockName().trim().isEmpty()) {

                throw new CustomException(
                        400,
                        "Dock name is required!"
                );
            }

            if (dto.getLocationAddress() == null ||
                    dto.getLocationAddress().trim().isEmpty()) {

                throw new CustomException(
                        400,
                        "Dock location address is required!"
                );
            }

            Dock dock = new Dock();

            dock.setDockName(dto.getDockName());
            dock.setLocationAddress(dto.getLocationAddress());
            dock.setMaxCapacity(dto.getMaxCapacity());

            Dock savedDock =
                    dockRepository.save(dock);

            DockDTO responseDTO = new DockDTO();

            responseDTO.setDockId(
                    savedDock.getDockId()
            );
            responseDTO.setDockName(
                    savedDock.getDockName()
            );
            responseDTO.setLocationAddress(
                    savedDock.getLocationAddress()
            );
            responseDTO.setMaxCapacity(
                    savedDock.getMaxCapacity()
            );

            log.info("Dock saved successfully");

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while saving dock: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public DockDTO updateDock(Long id, DockDTO dto) {
        log.info("Execute method updateDock()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Valid Dock ID is required for update!"
                );
            }

            if (dto == null) {
                throw new CustomException(
                        400,
                        "Dock data cannot be null!"
                );
            }

            Optional<Dock> optionalDock =
                    dockRepository.findById(id);

            if (optionalDock.isEmpty()) {
                log.error(
                        "Dock with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Dock not found with id: " + id
                );
            }

            Dock dock = optionalDock.get();

            if (dto.getDockName() != null &&
                    !dto.getDockName().trim().isEmpty()) {

                dock.setDockName(dto.getDockName());
            }

            if (dto.getLocationAddress() != null &&
                    !dto.getLocationAddress().trim().isEmpty()) {

                dock.setLocationAddress(
                        dto.getLocationAddress()
                );
            }

            dock.setMaxCapacity(
                    dto.getMaxCapacity()
            );

            Dock updatedDock =
                    dockRepository.save(dock);

            DockDTO responseDTO = new DockDTO();

            responseDTO.setDockId(
                    updatedDock.getDockId()
            );
            responseDTO.setDockName(
                    updatedDock.getDockName()
            );
            responseDTO.setLocationAddress(
                    updatedDock.getLocationAddress()
            );
            responseDTO.setMaxCapacity(
                    updatedDock.getMaxCapacity()
            );

            log.info("Dock updated successfully");

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while updating dock: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DockDTO getDockById(Long id) {
        log.info("Execute method getDockById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Dock ID: " + id
                );
            }

            Optional<Dock> optionalDock =
                    dockRepository.findById(id);

            if (optionalDock.isEmpty()) {
                log.error(
                        "Dock with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Dock not found with id: " + id
                );
            }

            Dock dock = optionalDock.get();

            DockDTO dto = new DockDTO();

            dto.setDockId(
                    dock.getDockId()
            );
            dto.setDockName(
                    dock.getDockName()
            );
            dto.setLocationAddress(
                    dock.getLocationAddress()
            );
            dto.setMaxCapacity(
                    dock.getMaxCapacity()
            );

            return dto;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching dock: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DockDTO> getAllDocks() {
        log.info("Execute method getAllDocks()");

        try {
            List<Dock> docks =
                    dockRepository.findAll();

            List<DockDTO> responseList =
                    new ArrayList<>();

            for (Dock dock : docks) {

                DockDTO dockDTO = new DockDTO();

                dockDTO.setDockId(
                        dock.getDockId()
                );
                dockDTO.setDockName(
                        dock.getDockName()
                );
                dockDTO.setLocationAddress(
                        dock.getLocationAddress()
                );
                dockDTO.setMaxCapacity(
                        dock.getMaxCapacity()
                );

                responseList.add(dockDTO);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching all docks: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteDock(Long id) {
        log.info("Execute method deleteDock()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Dock ID: " + id
                );
            }

            Optional<Dock> optionalDock =
                    dockRepository.findById(id);

            if (optionalDock.isEmpty()) {
                log.error(
                        "Dock with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Dock not found with id: " + id
                );
            }

            dockRepository.deleteById(id);

            log.info("Dock deleted successfully");

        } catch (Exception e) {
            log.error(
                    "Error occurred while deleting dock: {}",
                    e.getMessage()
            );
            throw e;
        }
    }
}