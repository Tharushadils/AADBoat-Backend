package lk.ijse.boat_reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BoatReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoatReservationApplication.class, args);
	}
}