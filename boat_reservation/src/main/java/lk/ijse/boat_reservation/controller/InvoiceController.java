//package lk.ijse.boat_reservation.controller;
//
//import lk.ijse.boat_reservation.constant.CommonResponse;
//import lk.ijse.boat_reservation.dto.InvoiceDTO;
//import lk.ijse.boat_reservation.service.InvoiceService;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
//import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;
//
//@RestController
//@RequestMapping("/api/invoices")
//public class InvoiceController {
//
//    private final InvoiceService invoiceService;
//
//    public InvoiceController(InvoiceService invoiceService) {
//        this.invoiceService = invoiceService;
//    }
//
//    @PostMapping(value = "/generate/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse generateInvoice(@PathVariable long reservationId) {
//        InvoiceDTO invoice = invoiceService.generateInvoice(reservationId);
//        return new CommonResponse(OPERATION_SUCCESS, invoice, SUCCESS_MESSAGE);
//    }
//
//    @GetMapping(value = "/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse getInvoiceById(@PathVariable long invoiceId) {
//        InvoiceDTO invoice = invoiceService.getInvoiceById(invoiceId);
//        return new CommonResponse(OPERATION_SUCCESS, invoice, SUCCESS_MESSAGE);
//    }
//
//    @GetMapping(value = "/reservation/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse getInvoiceByReservation(@PathVariable long reservationId) {
//        InvoiceDTO invoice = invoiceService.getInvoiceByReservation(reservationId);
//        return new CommonResponse(OPERATION_SUCCESS, invoice, SUCCESS_MESSAGE);
//    }
//
//    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse getAllInvoices() {
//        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
//        return new CommonResponse(OPERATION_SUCCESS, invoices, SUCCESS_MESSAGE);
//    }
//}