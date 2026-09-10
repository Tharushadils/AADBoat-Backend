package lk.ijse.boat_reservation.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.boat_reservation.dto.AddOnServiceDTO;
import lk.ijse.boat_reservation.entity.AddOnService;
import lk.ijse.boat_reservation.enumeration.AddOnServiceStatus;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.AddOnServiceRepository;
import lk.ijse.boat_reservation.service.AddOnServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddOnServiceServiceImpl implements AddOnServiceService {

    private final AddOnServiceRepository addOnServiceRepository;

    public AddOnServiceServiceImpl(AddOnServiceRepository addOnServiceRepository) {
        this.addOnServiceRepository = addOnServiceRepository;
    }

    @Override
    public AddOnServiceDTO saveAddOnService(AddOnServiceDTO addOnServiceDTO) {
        log.info("Execute method saveAddOnService()");

        try {
            if (addOnServiceDTO == null) {
                throw new CustomException(400, "Add-on service data cannot be null!");
            }

            if (addOnServiceDTO.getServiceName() == null ||
                    addOnServiceDTO.getServiceName().trim().isEmpty()) {
                throw new CustomException(400, "Service name is required!");
            }

            if (addOnServiceDTO.getPrice() == null) {
                throw new CustomException(400, "Service price is required!");
            }

            AddOnService addOnService = new AddOnService();

            addOnService.setServiceName(addOnServiceDTO.getServiceName());
            addOnService.setPrice(addOnServiceDTO.getPrice());
            addOnService.setDescription(addOnServiceDTO.getDescription());
            addOnService.setStatus(AddOnServiceStatus.ACTIVE);

            AddOnService savedAddOnService =
                    addOnServiceRepository.save(addOnService);

            log.info("Add-on service saved successfully");

            AddOnServiceDTO responseDTO = new AddOnServiceDTO();

            responseDTO.setServiceId(savedAddOnService.getServiceId());
            responseDTO.setServiceName(savedAddOnService.getServiceName());
            responseDTO.setPrice(savedAddOnService.getPrice());
            responseDTO.setDescription(savedAddOnService.getDescription());

            log.info("Save Add-on Service Returned ...");

            return responseDTO;

        } catch (Exception e) {
            log.error("Error occurred while saving add-on service: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public AddOnServiceDTO updateAddOnService(Long id, AddOnServiceDTO addOnServiceDTO) {
        log.info("Execute method updateAddOnService()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Valid Add-on Service ID is required for update!"
                );
            }

            if (addOnServiceDTO == null) {
                throw new CustomException(
                        400,
                        "Add-on service data cannot be null!"
                );
            }

            Optional<AddOnService> optionalAddOnService =
                    addOnServiceRepository.findById(id);

            if (optionalAddOnService.isEmpty()) {
                log.error("Add-on service with id {} does not exist", id);

                throw new CustomException(
                        404,
                        "Add-on service not found with id: " + id
                );
            }

            AddOnService addOnService = optionalAddOnService.get();

            if (addOnServiceDTO.getServiceName() != null &&
                    !addOnServiceDTO.getServiceName().trim().isEmpty()) {
                addOnService.setServiceName(addOnServiceDTO.getServiceName());
            }

            if (addOnServiceDTO.getPrice() != null) {
                addOnService.setPrice(addOnServiceDTO.getPrice());
            }

            if (addOnServiceDTO.getDescription() != null) {
                addOnService.setDescription(addOnServiceDTO.getDescription());
            }

            AddOnService updatedAddOnService =
                    addOnServiceRepository.save(addOnService);

            log.info("Add-on service updated successfully");

            AddOnServiceDTO responseDTO = new AddOnServiceDTO();

            responseDTO.setServiceId(updatedAddOnService.getServiceId());
            responseDTO.setServiceName(updatedAddOnService.getServiceName());
            responseDTO.setPrice(updatedAddOnService.getPrice());
            responseDTO.setDescription(updatedAddOnService.getDescription());

            return responseDTO;

        } catch (Exception e) {
            log.error("Error occurred while updating add-on service: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public AddOnServiceDTO getAddOnServiceById(Long id) {
        log.info("Execute method getAddOnServiceById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Add-on Service ID: " + id
                );
            }

            Optional<AddOnService> optionalAddOnService =
                    addOnServiceRepository.findById(id);

            if (optionalAddOnService.isEmpty()) {
                log.error("Add-on service with id {} does not exist", id);

                throw new CustomException(
                        404,
                        "Add-on service not found with id: " + id
                );
            }

            AddOnService addOnService = optionalAddOnService.get();

            AddOnServiceDTO responseDTO = new AddOnServiceDTO();

            responseDTO.setServiceId(addOnService.getServiceId());
            responseDTO.setServiceName(addOnService.getServiceName());
            responseDTO.setPrice(addOnService.getPrice());
            responseDTO.setDescription(addOnService.getDescription());

            return responseDTO;

        } catch (Exception e) {
            log.error("Error occurred while fetching add-on service: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<AddOnServiceDTO> getAllAddOnServices() {
        log.info("Execute method getAllAddOnServices()");

        try {
            List<AddOnService> addOnServices =
                    addOnServiceRepository.findAll();

            List<AddOnServiceDTO> responseList = new ArrayList<>();

            for (AddOnService addOnService : addOnServices) {

                AddOnServiceDTO addOnServiceDTO = new AddOnServiceDTO();

                addOnServiceDTO.setServiceId(addOnService.getServiceId());
                addOnServiceDTO.setServiceName(addOnService.getServiceName());
                addOnServiceDTO.setPrice(addOnService.getPrice());
                addOnServiceDTO.setDescription(addOnService.getDescription());

                responseList.add(addOnServiceDTO);
            }

            return responseList;

        } catch (Exception e) {
            log.error("Error occurred while fetching all add-on services: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteAddOnService(Long id) {
        log.info("Execute method deleteAddOnService");

        try {
            Optional<AddOnService> addOnServiceOptional =
                    addOnServiceRepository.findById(id);

            if (!addOnServiceOptional.isPresent()) {

                log.error(
                        "AddOnService with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "AddOnService not found with id: " + id
                );
            }

            AddOnService addOnService =
                    addOnServiceOptional.get();

            addOnService.setStatus(
                    AddOnServiceStatus.DELETED
            );

            addOnServiceRepository.save(addOnService);

            log.info(
                    "AddOnService soft deleted successfully with id: {}",
                    id
            );

        } catch (Exception e) {

            log.error(
                    "Error occurred while deleting add-on service: {}",
                    e.getMessage()
            );

            throw e;
        }
    }
}