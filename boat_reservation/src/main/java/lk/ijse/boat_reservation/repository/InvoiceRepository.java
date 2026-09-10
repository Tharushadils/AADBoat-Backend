//package lk.ijse.boat_reservation.repository;
//
//import lk.ijse.boat_reservation.entity.Invoice;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
//    Optional<Invoice> findByReservationReservationId(Long reservationId);
//    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
//}