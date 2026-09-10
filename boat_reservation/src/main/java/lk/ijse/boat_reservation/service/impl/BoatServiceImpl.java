package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.BoatDTO;
import lk.ijse.boat_reservation.entity.Boat;
import lk.ijse.boat_reservation.entity.BoatCategory;
import lk.ijse.boat_reservation.entity.Dock;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.BoatCategoryRepository;
import lk.ijse.boat_reservation.repository.BoatRepository;
import lk.ijse.boat_reservation.repository.DockRepository;
import lk.ijse.boat_reservation.service.BoatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoatServiceImpl implements BoatService {

    private final BoatRepository boatRepository;
    private final DockRepository dockRepository;
    private final BoatCategoryRepository categoryRepository;

    public BoatServiceImpl(
            BoatRepository boatRepository,
            DockRepository dockRepository,
            BoatCategoryRepository categoryRepository) {

        this.boatRepository = boatRepository;
        this.dockRepository = dockRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public BoatDTO saveBoat(BoatDTO dto) {
        log.info("Execute method saveBoat()");

        try {
            if (dto == null) {
                throw new CustomException(
                        400,
                        "Boat data cannot be null!"
                );
            }

            if (dto.getBoatName() == null ||
                    dto.getBoatName().trim().isEmpty()) {

                throw new CustomException(
                        400,
                        "Boat name is required!"
                );
            }

            if (dto.getDockId() == null ||
                    dto.getDockId() <= 0) {

                throw new CustomException(
                        400,
                        "Valid Dock ID is required!"
                );
            }

            if (dto.getCategoryId() == null ||
                    dto.getCategoryId() <= 0) {

                throw new CustomException(
                        400,
                        "Valid Category ID is required!"
                );
            }

            Optional<Dock> dockOptional =
                    dockRepository.findById(dto.getDockId());

            if (dockOptional.isEmpty()) {
                log.error(
                        "Dock with id {} does not exist",
                        dto.getDockId()
                );

                throw new CustomException(
                        404,
                        "Dock not found with id: " + dto.getDockId()
                );
            }

            Optional<BoatCategory> categoryOptional =
                    categoryRepository.findById(dto.getCategoryId());

            if (categoryOptional.isEmpty()) {
                log.error(
                        "Category with id {} does not exist",
                        dto.getCategoryId()
                );

                throw new CustomException(
                        404,
                        "Category not found with id: "
                                + dto.getCategoryId()
                );
            }

            Boat boat = new Boat();

            boat.setBoatName(dto.getBoatName());
            boat.setPassengerCapacity(dto.getPassengerCapacity());
            boat.setBaseHourlyRate(dto.getBaseHourlyRate());
            boat.setStatus(dto.getStatus());
            boat.setDock(dockOptional.get());
            boat.setCategory(categoryOptional.get());

            Boat savedBoat = boatRepository.save(boat);

            BoatDTO responseDTO = new BoatDTO();

            responseDTO.setBoatId(savedBoat.getBoatId());
            responseDTO.setBoatName(savedBoat.getBoatName());
            responseDTO.setPassengerCapacity(
                    savedBoat.getPassengerCapacity()
            );
            responseDTO.setBaseHourlyRate(
                    savedBoat.getBaseHourlyRate()
            );
            responseDTO.setStatus(savedBoat.getStatus());

            if (savedBoat.getDock() != null) {
                responseDTO.setDockId(
                        savedBoat.getDock().getDockId()
                );
                responseDTO.setDockName(
                        savedBoat.getDock().getDockName()
                );
            }

            if (savedBoat.getCategory() != null) {
                responseDTO.setCategoryId(
                        savedBoat.getCategory().getCategoryId()
                );
                responseDTO.setCategoryName(
                        savedBoat.getCategory().getCategoryName()
                );
            }

            log.info("Boat saved successfully");

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while saving boat: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public BoatDTO updateBoat(Long id, BoatDTO dto) {
        log.info("Execute method updateBoat()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Valid Boat ID is required for update!"
                );
            }

            if (dto == null) {
                throw new CustomException(
                        400,
                        "Boat data cannot be null!"
                );
            }

            Optional<Boat> boatOptional =
                    boatRepository.findById(id);

            if (boatOptional.isEmpty()) {
                log.error(
                        "Boat with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Boat not found with id: " + id
                );
            }

            if (dto.getDockId() == null ||
                    dto.getDockId() <= 0) {

                throw new CustomException(
                        400,
                        "Valid Dock ID is required!"
                );
            }

            if (dto.getCategoryId() == null ||
                    dto.getCategoryId() <= 0) {

                throw new CustomException(
                        400,
                        "Valid Category ID is required!"
                );
            }

            Optional<Dock> dockOptional =
                    dockRepository.findById(dto.getDockId());

            if (dockOptional.isEmpty()) {
                log.error(
                        "Dock with id {} does not exist",
                        dto.getDockId()
                );

                throw new CustomException(
                        404,
                        "Dock not found with id: " + dto.getDockId()
                );
            }

            Optional<BoatCategory> categoryOptional =
                    categoryRepository.findById(dto.getCategoryId());

            if (categoryOptional.isEmpty()) {
                log.error(
                        "Category with id {} does not exist",
                        dto.getCategoryId()
                );

                throw new CustomException(
                        404,
                        "Category not found with id: "
                                + dto.getCategoryId()
                );
            }

            Boat boat = boatOptional.get();

            if (dto.getBoatName() != null &&
                    !dto.getBoatName().trim().isEmpty()) {

                boat.setBoatName(dto.getBoatName());
            }


            boat.setPassengerCapacity(
                    dto.getPassengerCapacity()
            );

            boat.setBaseHourlyRate(
                    dto.getBaseHourlyRate()
            );

            if (dto.getStatus() != null) {
                boat.setStatus(dto.getStatus());
            }

            boat.setDock(dockOptional.get());
            boat.setCategory(categoryOptional.get());

            Boat updatedBoat =
                    boatRepository.save(boat);

            BoatDTO responseDTO = new BoatDTO();

            responseDTO.setBoatId(updatedBoat.getBoatId());
            responseDTO.setBoatName(updatedBoat.getBoatName());
            responseDTO.setPassengerCapacity(
                    updatedBoat.getPassengerCapacity()
            );
            responseDTO.setBaseHourlyRate(
                    updatedBoat.getBaseHourlyRate()
            );
            responseDTO.setStatus(updatedBoat.getStatus());

            if (updatedBoat.getDock() != null) {
                responseDTO.setDockId(
                        updatedBoat.getDock().getDockId()
                );
                responseDTO.setDockName(
                        updatedBoat.getDock().getDockName()
                );
            }

            if (updatedBoat.getCategory() != null) {
                responseDTO.setCategoryId(
                        updatedBoat.getCategory().getCategoryId()
                );
                responseDTO.setCategoryName(
                        updatedBoat.getCategory().getCategoryName()
                );
            }

            log.info("Boat updated successfully");

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while updating boat: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BoatDTO getBoatById(Long id) {
        log.info("Execute method getBoatById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Boat ID: " + id
                );
            }

            Optional<Boat> optionalBoat =
                    boatRepository.findById(id);

            if (optionalBoat.isEmpty()) {
                log.error(
                        "Boat with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Boat not found with id: " + id
                );
            }

            Boat boat = optionalBoat.get();

            BoatDTO dto = new BoatDTO();

            dto.setBoatId(boat.getBoatId());
            dto.setBoatName(boat.getBoatName());
            dto.setPassengerCapacity(
                    boat.getPassengerCapacity()
            );
            dto.setBaseHourlyRate(
                    boat.getBaseHourlyRate()
            );
            dto.setStatus(boat.getStatus());

            if (boat.getDock() != null) {
                dto.setDockId(
                        boat.getDock().getDockId()
                );
                dto.setDockName(
                        boat.getDock().getDockName()
                );
            }

            if (boat.getCategory() != null) {
                dto.setCategoryId(
                        boat.getCategory().getCategoryId()
                );
                dto.setCategoryName(
                        boat.getCategory().getCategoryName()
                );
            }

            return dto;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching boat: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoatDTO> getAllBoats() {
        log.info("Execute method getAllBoats()");

        try {
            List<Boat> boats =
                    boatRepository.findAll();

            List<BoatDTO> responseList =
                    new ArrayList<>();

            for (Boat boat : boats) {

                BoatDTO boatDTO = new BoatDTO();

                boatDTO.setBoatId(boat.getBoatId());
                boatDTO.setBoatName(boat.getBoatName());
                boatDTO.setPassengerCapacity(
                        boat.getPassengerCapacity()
                );
                boatDTO.setBaseHourlyRate(
                        boat.getBaseHourlyRate()
                );
                boatDTO.setStatus(boat.getStatus());

                if (boat.getDock() != null) {
                    boatDTO.setDockId(
                            boat.getDock().getDockId()
                    );
                    boatDTO.setDockName(
                            boat.getDock().getDockName()
                    );
                }

                if (boat.getCategory() != null) {
                    boatDTO.setCategoryId(
                            boat.getCategory().getCategoryId()
                    );
                    boatDTO.setCategoryName(
                            boat.getCategory().getCategoryName()
                    );
                }

                responseList.add(boatDTO);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching all boats: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoatDTO> getBoatsByDock(Long dockId) {
        log.info("Execute method getBoatsByDock()");

        try {
            if (dockId == null || dockId <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Dock ID: " + dockId
                );
            }

            List<Boat> boats =
                    boatRepository.findByDock_DockId(dockId);

            List<BoatDTO> responseList =
                    new ArrayList<>();

            for (Boat boat : boats) {

                BoatDTO boatDTO = new BoatDTO();

                boatDTO.setBoatId(boat.getBoatId());
                boatDTO.setBoatName(boat.getBoatName());
                boatDTO.setPassengerCapacity(
                        boat.getPassengerCapacity()
                );
                boatDTO.setBaseHourlyRate(
                        boat.getBaseHourlyRate()
                );
                boatDTO.setStatus(boat.getStatus());

                if (boat.getDock() != null) {
                    boatDTO.setDockId(
                            boat.getDock().getDockId()
                    );
                    boatDTO.setDockName(
                            boat.getDock().getDockName()
                    );
                }

                if (boat.getCategory() != null) {
                    boatDTO.setCategoryId(
                            boat.getCategory().getCategoryId()
                    );
                    boatDTO.setCategoryName(
                            boat.getCategory().getCategoryName()
                    );
                }

                responseList.add(boatDTO);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching boats by dock: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoatDTO> getBoatsByCategory(Long categoryId) {
        log.info("Execute method getBoatsByCategory()");

        try {
            if (categoryId == null || categoryId <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Category ID: " + categoryId
                );
            }

            List<Boat> boats =
                    boatRepository.findByCategory_CategoryId(
                            categoryId
                    );

            List<BoatDTO> responseList =
                    new ArrayList<>();

            for (Boat boat : boats) {

                BoatDTO boatDTO = new BoatDTO();

                boatDTO.setBoatId(boat.getBoatId());
                boatDTO.setBoatName(boat.getBoatName());
                boatDTO.setPassengerCapacity(
                        boat.getPassengerCapacity()
                );
                boatDTO.setBaseHourlyRate(
                        boat.getBaseHourlyRate()
                );
                boatDTO.setStatus(boat.getStatus());

                if (boat.getDock() != null) {
                    boatDTO.setDockId(
                            boat.getDock().getDockId()
                    );
                    boatDTO.setDockName(
                            boat.getDock().getDockName()
                    );
                }

                if (boat.getCategory() != null) {
                    boatDTO.setCategoryId(
                            boat.getCategory().getCategoryId()
                    );
                    boatDTO.setCategoryName(
                            boat.getCategory().getCategoryName()
                    );
                }

                responseList.add(boatDTO);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching boats by category: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteBoat(Long id) {
        log.info("Execute method deleteBoat()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Boat ID: " + id
                );
            }

            Optional<Boat> optionalBoat =
                    boatRepository.findById(id);

            if (optionalBoat.isEmpty()) {
                log.error(
                        "Boat with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Boat not found with id: " + id
                );
            }

            boatRepository.deleteById(id);

            log.info("Boat deleted successfully");

        } catch (Exception e) {
            log.error(
                    "Error occurred while deleting boat: {}",
                    e.getMessage()
            );
            throw e;
        }
    }
}