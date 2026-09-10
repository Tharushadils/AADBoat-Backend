package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {
    PaymentDTO processPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getPaymentsByReservation(Long reservationId);
    List<PaymentDTO> getAllPayments();
}