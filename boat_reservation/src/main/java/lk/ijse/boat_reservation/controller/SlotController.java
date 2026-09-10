package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.SlotDTO;
import lk.ijse.boat_reservation.service.SlotService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveSlot(@RequestBody SlotDTO dto) {
        slotService.saveSlot(dto);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PutMapping(value = "/{slotId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateSlot(@PathVariable long slotId, @RequestBody SlotDTO dto) {
        SlotDTO updated = slotService.updateSlot(slotId, dto);
        return new CommonResponse(OPERATION_SUCCESS, updated, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{slotId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteSlot(@PathVariable long slotId) {
        slotService.deleteSlot(slotId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{slotId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getSlotById(@PathVariable long slotId) {
        SlotDTO dto = slotService.getSlotById(slotId);
        return new CommonResponse(OPERATION_SUCCESS, dto, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllSlots() {
        List<SlotDTO> slots = slotService.getAllSlots();
        return new CommonResponse(OPERATION_SUCCESS, slots, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAvailableSlotsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<SlotDTO> slots = slotService.getAvailableSlotsByDate(date);
        return new CommonResponse(OPERATION_SUCCESS, slots, SUCCESS_MESSAGE);
    }
}