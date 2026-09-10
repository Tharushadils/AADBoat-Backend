package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.DockDTO;
import lk.ijse.boat_reservation.service.DockService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/docks")
public class DockController {

    private final DockService dockService;

    public DockController(DockService dockService) {
        this.dockService = dockService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveDock(@RequestBody DockDTO dockDTO) {
        dockService.saveDock(dockDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateDock(@RequestBody DockDTO dockDTO) {
        DockDTO updatedDock = dockService.updateDock(dockDTO.getDockId(), dockDTO);
        return new CommonResponse(OPERATION_SUCCESS, updatedDock, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{dockId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteDock(@PathVariable long dockId) {
        dockService.deleteDock(dockId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{dockId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getDockById(@PathVariable long dockId) {
        DockDTO dock = dockService.getDockById(dockId);
        return new CommonResponse(OPERATION_SUCCESS, dock, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllDocks() {
        List<DockDTO> docks = dockService.getAllDocks();
        return new CommonResponse(OPERATION_SUCCESS, docks, SUCCESS_MESSAGE);
    }
}