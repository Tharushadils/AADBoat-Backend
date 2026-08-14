package lk.ijse.boat_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private long reviewId;
    private int ratingStars;
    private String comment;
    private LocalDate reviewDate;
    private Long userId;
    private String userName;
    private Long boatId;
    private String boatName;
}