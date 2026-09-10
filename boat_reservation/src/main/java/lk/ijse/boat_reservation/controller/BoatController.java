package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.BoatDTO;
import lk.ijse.boat_reservation.service.BoatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/boats")
public class BoatController {

    private final BoatService boatService;

    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveBoat(@RequestBody BoatDTO boatDTO) {
        boatService.saveBoat(boatDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateBoat(@RequestBody BoatDTO boatDTO) {
        BoatDTO updatedBoat = boatService.updateBoat(boatDTO.getBoatId(), boatDTO);
        return new CommonResponse(OPERATION_SUCCESS, updatedBoat, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{boatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteBoat(@PathVariable long boatId) {
        boatService.deleteBoat(boatId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{boatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getBoatById(@PathVariable long boatId) {
        BoatDTO boat = boatService.getBoatById(boatId);
        return new CommonResponse(OPERATION_SUCCESS, boat, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllBoats() {
        List<BoatDTO> boats = boatService.getAllBoats();
        return new CommonResponse(OPERATION_SUCCESS, boats, SUCCESS_MESSAGE);
    }
}