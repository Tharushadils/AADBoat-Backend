package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.PaymentDTO;
import lk.ijse.boat_reservation.entity.Payment;
import lk.ijse.boat_reservation.entity.Reservation;
import lk.ijse.boat_reservation.enumeration.PaymentStatus;
import lk.ijse.boat_reservation.enumeration.ReservationStatus;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.PaymentRepository;
import lk.ijse.boat_reservation.repository.ReservationRepository;
import lk.ijse.boat_reservation.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            ReservationRepository reservationRepository) {

        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public PaymentDTO processPayment(PaymentDTO dto) {
        log.info("Execute method processPayment()");

        try {
            if (dto == null) {
                throw new CustomException(
                        400,
                        "Payment data cannot be null!"
                );
            }

            if (dto.getReservationId() == null ||
                    dto.getReservationId() <= 0) {

                throw new CustomException(
                        400,
                        "Valid Reservation ID is required!"
                );
            }

            Optional<Reservation> reservationOptional =
                    reservationRepository.findById(
                            dto.getReservationId()
                    );

            if (reservationOptional.isEmpty()) {
                log.error(
                        "Reservation with id {} does not exist",
                        dto.getReservationId()
                );

                throw new CustomException(
                        404,
                        "Reservation not found with id: "
                                + dto.getReservationId()
                );
            }

            Reservation reservation =
                    reservationOptional.get();

            Payment payment = new Payment();

            payment.setTransactionId(
                    "TXN-" +
                            UUID.randomUUID()
                                    .toString()
                                    .substring(0, 8)
                                    .toUpperCase()
            );

            payment.setAmountPaid(
                    dto.getAmountPaid()
            );

            payment.setPaymentDate(
                    LocalDateTime.now()
            );

            payment.setPaymentMethod(
                    dto.getPaymentMethod()
            );

            payment.setPaymentStatus(
                    PaymentStatus.COMPLETED.name()
            );

            payment.setReservation(
                    reservation
            );

            reservation.setStatus(
                    ReservationStatus.CONFIRMED.name()
            );

            reservationRepository.save(reservation);

            Payment savedPayment =
                    paymentRepository.save(payment);

            PaymentDTO responseDTO =
                    new PaymentDTO();

            responseDTO.setPaymentId(
                    savedPayment.getPaymentId()
            );

            responseDTO.setTransactionId(
                    savedPayment.getTransactionId()
            );

            responseDTO.setAmountPaid(
                    savedPayment.getAmountPaid()
            );

            responseDTO.setPaymentDate(
                    savedPayment.getPaymentDate()
            );

            responseDTO.setPaymentMethod(
                    savedPayment.getPaymentMethod()
            );

            responseDTO.setPaymentStatus(
                    savedPayment.getPaymentStatus()
            );

            if (savedPayment.getReservation() != null) {
                responseDTO.setReservationId(
                        savedPayment
                                .getReservation()
                                .getReservationId()
                );
            }

            log.info("Payment processed successfully");

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while processing payment: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentDTO getPaymentById(Long id) {
        log.info("Execute method getPaymentById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Payment ID: " + id
                );
            }

            Optional<Payment> paymentOptional =
                    paymentRepository.findById(id);

            if (paymentOptional.isEmpty()) {
                log.error(
                        "Payment with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Payment not found with id: " + id
                );
            }

            Payment payment =
                    paymentOptional.get();

            PaymentDTO dto =
                    new PaymentDTO();

            dto.setPaymentId(
                    payment.getPaymentId()
            );

            dto.setTransactionId(
                    payment.getTransactionId()
            );

            dto.setAmountPaid(
                    payment.getAmountPaid()
            );

            dto.setPaymentDate(
                    payment.getPaymentDate()
            );

            dto.setPaymentMethod(
                    payment.getPaymentMethod()
            );

            dto.setPaymentStatus(
                    payment.getPaymentStatus()
            );

            if (payment.getReservation() != null) {
                dto.setReservationId(
                        payment
                                .getReservation()
                                .getReservationId()
                );
            }

            return dto;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching payment: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPaymentsByReservation(
            Long reservationId) {

        log.info(
                "Execute method getPaymentsByReservation()"
        );

        try {
            if (reservationId == null ||
                    reservationId <= 0) {

                throw new CustomException(
                        400,
                        "Invalid Reservation ID: "
                                + reservationId
                );
            }

            List<Payment> payments =
                    paymentRepository
                            .findByReservationReservationId(
                                    reservationId
                            );

            List<PaymentDTO> responseList =
                    new ArrayList<>();

            for (Payment payment : payments) {

                PaymentDTO dto =
                        new PaymentDTO();

                dto.setPaymentId(
                        payment.getPaymentId()
                );

                dto.setTransactionId(
                        payment.getTransactionId()
                );

                dto.setAmountPaid(
                        payment.getAmountPaid()
                );

                dto.setPaymentDate(
                        payment.getPaymentDate()
                );

                dto.setPaymentMethod(
                        payment.getPaymentMethod()
                );

                dto.setPaymentStatus(
                        payment.getPaymentStatus()
                );

                if (payment.getReservation() != null) {
                    dto.setReservationId(
                            payment
                                    .getReservation()
                                    .getReservationId()
                    );
                }

                responseList.add(dto);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching payments by reservation: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getAllPayments() {
        log.info("Execute method getAllPayments()");

        try {
            List<Payment> payments =
                    paymentRepository.findAll();

            List<PaymentDTO> responseList =
                    new ArrayList<>();

            for (Payment payment : payments) {

                PaymentDTO dto =
                        new PaymentDTO();

                dto.setPaymentId(
                        payment.getPaymentId()
                );

                dto.setTransactionId(
                        payment.getTransactionId()
                );

                dto.setAmountPaid(
                        payment.getAmountPaid()
                );

                dto.setPaymentDate(
                        payment.getPaymentDate()
                );

                dto.setPaymentMethod(
                        payment.getPaymentMethod()
                );

                dto.setPaymentStatus(
                        payment.getPaymentStatus()
                );

                if (payment.getReservation() != null) {
                    dto.setReservationId(
                            payment
                                    .getReservation()
                                    .getReservationId()
                    );
                }

                responseList.add(dto);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching all payments: {}",
                    e.getMessage()
            );
            throw e;
        }
    }
}