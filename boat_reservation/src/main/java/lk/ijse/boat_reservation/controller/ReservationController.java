package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.ReservationDTO;
import lk.ijse.boat_reservation.service.ReservationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")//use to access in different origin
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse saveReservation(
            @RequestBody ReservationDTO reservationDTO
    ) {

        ReservationDTO created = reservationService.createReservation(reservationDTO);

        return new CommonResponse(
                OPERATION_SUCCESS,
                created,
                SUCCESS_MESSAGE
        );
    }


    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse updateReservation(
            @RequestBody ReservationDTO reservationDTO
    ) {

        ReservationDTO updated =
                reservationService.updateReservation(reservationDTO);

        return new CommonResponse(
                OPERATION_SUCCESS,
                updated,
                SUCCESS_MESSAGE
        );
    }


    @DeleteMapping(
            value = "/{reservationId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse deleteReservation(
            @PathVariable long reservationId
    ) {

        reservationService.cancelReservation(reservationId);

        return new CommonResponse(
                OPERATION_SUCCESS,
                SUCCESS_MESSAGE
        );
    }




    @GetMapping(
            value = "/{reservationId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse getReservationById(
            @PathVariable long reservationId
    ) {

        ReservationDTO reservation =
                reservationService.getReservationById(reservationId);

        return new CommonResponse(
                OPERATION_SUCCESS,
                reservation,
                SUCCESS_MESSAGE
        );
    }


    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse getAllReservations() {

        List<ReservationDTO> reservations =
                reservationService.getAllReservations();

        return new CommonResponse(
                OPERATION_SUCCESS,
                reservations,
                SUCCESS_MESSAGE
        );
    }
}