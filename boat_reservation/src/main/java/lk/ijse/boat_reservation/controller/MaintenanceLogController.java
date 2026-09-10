package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.MaintenanceLogDTO;
import lk.ijse.boat_reservation.service.MaintenanceLogService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceLogController {

    private final MaintenanceLogService maintenanceLogService;

    public MaintenanceLogController(MaintenanceLogService maintenanceLogService) {
        this.maintenanceLogService = maintenanceLogService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse createLog(@RequestBody MaintenanceLogDTO dto) {
        maintenanceLogService.createLog(dto);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateLog(@RequestBody MaintenanceLogDTO dto) {
        MaintenanceLogDTO updated = maintenanceLogService.updateLog(dto.getLogId(), dto);
        return new CommonResponse(OPERATION_SUCCESS, updated, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{logId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteLog(@PathVariable long logId) {
        maintenanceLogService.deleteLog(logId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{logId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getLogById(@PathVariable long logId) {
        MaintenanceLogDTO log = maintenanceLogService.getLogById(logId);
        return new CommonResponse(OPERATION_SUCCESS, log, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/boat/{boatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getLogsByBoat(@PathVariable long boatId) {
        List<MaintenanceLogDTO> logs = maintenanceLogService.getLogsByBoat(boatId);
        return new CommonResponse(OPERATION_SUCCESS, logs, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllLogs() {
        List<MaintenanceLogDTO> logs = maintenanceLogService.getAllLogs();
        return new CommonResponse(OPERATION_SUCCESS, logs, SUCCESS_MESSAGE);
    }
}