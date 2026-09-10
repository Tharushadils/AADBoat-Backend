package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.PaymentDTO;
import lk.ijse.boat_reservation.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse processPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentDTO processedPayment = paymentService.processPayment(paymentDTO);
        return new CommonResponse(OPERATION_SUCCESS, processedPayment, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getPaymentById(@PathVariable long paymentId) {
        PaymentDTO payment = paymentService.getPaymentById(paymentId);
        return new CommonResponse(OPERATION_SUCCESS, payment, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/reservation/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getPaymentsByReservation(@PathVariable long reservationId) {
        List<PaymentDTO> payments = paymentService.getPaymentsByReservation(reservationId);
        return new CommonResponse(OPERATION_SUCCESS, payments, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return new CommonResponse(OPERATION_SUCCESS, payments, SUCCESS_MESSAGE);
    }
}