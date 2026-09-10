package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.AddOnServiceDTO;
import lk.ijse.boat_reservation.service.AddOnServiceService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/add-ons")
public class AddOnServiceController {

    private final AddOnServiceService addOnServiceService;

    public AddOnServiceController(AddOnServiceService addOnServiceService) {
        this.addOnServiceService = addOnServiceService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveAddOnService(@RequestBody AddOnServiceDTO dto) {
        addOnServiceService.saveAddOnService(dto);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateAddOnService(@RequestBody AddOnServiceDTO dto) {
        AddOnServiceDTO updated = addOnServiceService.updateAddOnService(dto.getServiceId(), dto);
        return new CommonResponse(OPERATION_SUCCESS, updated, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{serviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteAddOnService(@PathVariable long serviceId) {
        addOnServiceService.deleteAddOnService(serviceId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{serviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAddOnServiceById(@PathVariable long serviceId) {
        AddOnServiceDTO dto = addOnServiceService.getAddOnServiceById(serviceId);
        return new CommonResponse(OPERATION_SUCCESS, dto, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllAddOnServices() {
        List<AddOnServiceDTO> services = addOnServiceService.getAllAddOnServices();
        return new CommonResponse(OPERATION_SUCCESS, services, SUCCESS_MESSAGE);
    }
}