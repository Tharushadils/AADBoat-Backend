//package lk.ijse.boat_reservation.service.impl;
//
//import lk.ijse.boat_reservation.dto.InvoiceDTO;
//import lk.ijse.boat_reservation.entity.Invoice;
//import lk.ijse.boat_reservation.entity.Reservation;
//import lk.ijse.boat_reservation.exception.CustomException;
//import lk.ijse.boat_reservation.repository.InvoiceRepository;
//import lk.ijse.boat_reservation.repository.ReservationRepository;
//import lk.ijse.boat_reservation.service.InvoiceService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@Slf4j
//public class InvoiceServiceImpl implements InvoiceService {
//
//    private final InvoiceRepository invoiceRepository;
//    private final ReservationRepository reservationRepository;
//
//    public InvoiceServiceImpl(
//            InvoiceRepository invoiceRepository,
//            ReservationRepository reservationRepository) {
//
//        this.invoiceRepository = invoiceRepository;
//        this.reservationRepository = reservationRepository;
//    }
//
//    @Override
//    @Transactional
//    public InvoiceDTO generateInvoice(Long reservationId) {
//        log.info("Execute method generateInvoice()");
//
//        try {
//            if (reservationId == null || reservationId <= 0) {
//                throw new CustomException(
//                        400,
//                        "Valid Reservation ID is required!"
//                );
//            }
//
//            Optional<Reservation> reservationOptional =
//                    reservationRepository.findById(reservationId);
//
//            if (reservationOptional.isEmpty()) {
//                log.error(
//                        "Reservation with id {} does not exist",
//                        reservationId
//                );
//
//                throw new CustomException(
//                        404,
//                        "Reservation not found with id: "
//                                + reservationId
//                );
//            }
//
//            Reservation reservation =
//                    reservationOptional.get();
//
//            Invoice invoice = new Invoice();
//
//            invoice.setInvoiceNumber(
//                    "INV-" +
//                            UUID.randomUUID()
//                                    .toString()
//                                    .substring(0, 8)
//                                    .toUpperCase()
//            );
//
//            invoice.setIssueDate(
//                    LocalDateTime.now()
//            );
//
//            invoice.setTotalAmount(
//                    reservation.getTotalPrice()
//            );
//
//            invoice.setReservation(
//                    reservation
//            );
//
//            Invoice savedInvoice =
//                    invoiceRepository.save(invoice);
//
//            InvoiceDTO responseDTO =
//                    new InvoiceDTO();
//
//            responseDTO.setInvoiceId(
//                    savedInvoice.getInvoiceId()
//            );
//
//            responseDTO.setInvoiceNumber(
//                    savedInvoice.getInvoiceNumber()
//            );
//
//            responseDTO.setIssueDate(
//                    savedInvoice.getIssueDate()
//            );
//
//            responseDTO.setTotalAmount(
//                    savedInvoice.getTotalAmount()
//            );
//
//            if (savedInvoice.getReservation() != null) {
//                responseDTO.setReservationId(
//                        savedInvoice
//                                .getReservation()
//                                .getReservationId()
//                );
//            }
//
//            log.info("Invoice generated successfully");
//
//            return responseDTO;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while generating invoice: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public InvoiceDTO getInvoiceById(Long id) {
//        log.info("Execute method getInvoiceById()");
//
//        try {
//            if (id == null || id <= 0) {
//                throw new CustomException(
//                        400,
//                        "Invalid Invoice ID: " + id
//                );
//            }
//
//            Optional<Invoice> invoiceOptional =
//                    invoiceRepository.findById(id);
//
//            if (invoiceOptional.isEmpty()) {
//                log.error(
//                        "Invoice with id {} does not exist",
//                        id
//                );
//
//                throw new CustomException(
//                        404,
//                        "Invoice not found with id: " + id
//                );
//            }
//
//            Invoice invoice =
//                    invoiceOptional.get();
//
//            InvoiceDTO dto =
//                    new InvoiceDTO();
//
//            dto.setInvoiceId(
//                    invoice.getInvoiceId()
//            );
//
//            dto.setInvoiceNumber(
//                    invoice.getInvoiceNumber()
//            );
//
//            dto.setIssueDate(
//                    invoice.getIssueDate()
//            );
//
//            dto.setTotalAmount(
//                    invoice.getTotalAmount()
//            );
//
//            if (invoice.getReservation() != null) {
//                dto.setReservationId(
//                        invoice
//                                .getReservation()
//                                .getReservationId()
//                );
//            }
//
//            return dto;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while fetching invoice: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public InvoiceDTO getInvoiceByReservation(
//            Long reservationId) {
//
//        log.info(
//                "Execute method getInvoiceByReservation()"
//        );
//
//        try {
//            if (reservationId == null ||
//                    reservationId <= 0) {
//
//                throw new CustomException(
//                        400,
//                        "Invalid Reservation ID: "
//                                + reservationId
//                );
//            }
//
//            Optional<Invoice> invoiceOptional =
//                    invoiceRepository
//                            .findByReservationReservationId(
//                                    reservationId
//                            );
//
//            if (invoiceOptional.isEmpty()) {
//                log.error(
//                        "Invoice for reservation id {} does not exist",
//                        reservationId
//                );
//
//                throw new CustomException(
//                        404,
//                        "Invoice not found for Reservation ID: "
//                                + reservationId
//                );
//            }
//
//            Invoice invoice =
//                    invoiceOptional.get();
//
//            InvoiceDTO dto =
//                    new InvoiceDTO();
//
//            dto.setInvoiceId(
//                    invoice.getInvoiceId()
//            );
//
//            dto.setInvoiceNumber(
//                    invoice.getInvoiceNumber()
//            );
//
//            dto.setIssueDate(
//                    invoice.getIssueDate()
//            );
//
//            dto.setTotalAmount(
//                    invoice.getTotalAmount()
//            );
//
//            if (invoice.getReservation() != null) {
//                dto.setReservationId(
//                        invoice
//                                .getReservation()
//                                .getReservationId()
//                );
//            }
//
//            return dto;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while fetching invoice by reservation: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<InvoiceDTO> getAllInvoices() {
//        log.info("Execute method getAllInvoices()");
//
//        try {
//            List<Invoice> invoices =
//                    invoiceRepository.findAll();
//
//            List<InvoiceDTO> responseList =
//                    new ArrayList<>();
//
//            for (Invoice invoice : invoices) {
//
//                InvoiceDTO dto =
//                        new InvoiceDTO();
//
//                dto.setInvoiceId(
//                        invoice.getInvoiceId()
//                );
//
//                dto.setInvoiceNumber(
//                        invoice.getInvoiceNumber()
//                );
//
//                dto.setIssueDate(
//                        invoice.getIssueDate()
//                );
//
//                dto.setTotalAmount(
//                        invoice.getTotalAmount()
//                );
//
//                if (invoice.getReservation() != null) {
//                    dto.setReservationId(
//                            invoice
//                                    .getReservation()
//                                    .getReservationId()
//                    );
//                }
//
//                responseList.add(dto);
//            }
//
//            return responseList;
//
//        } catch (Exception e) {
//            log.error(
//                    "Error occurred while fetching all invoices: {}",
//                    e.getMessage()
//            );
//            throw e;
//        }
//    }
//}