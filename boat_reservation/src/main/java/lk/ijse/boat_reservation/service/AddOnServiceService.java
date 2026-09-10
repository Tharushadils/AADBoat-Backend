package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.AddOnServiceDTO;
import java.util.List;

public interface AddOnServiceService {
    AddOnServiceDTO saveAddOnService(AddOnServiceDTO dto);
    AddOnServiceDTO updateAddOnService(Long id, AddOnServiceDTO dto);
    AddOnServiceDTO getAddOnServiceById(Long id);
    List<AddOnServiceDTO> getAllAddOnServices();
    void deleteAddOnService(Long id);
}