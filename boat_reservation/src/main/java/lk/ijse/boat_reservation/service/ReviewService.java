package lk.ijse.boat_reservation.service;

import lk.ijse.boat_reservation.dto.ReviewDTO;
import java.util.List;

public interface ReviewService {
    ReviewDTO addReview(ReviewDTO dto);
    ReviewDTO getReviewById(Long id);
    List<ReviewDTO> getReviewsByBoat(Long boatId);
    List<ReviewDTO> getAllReviews();
    void deleteReview(Long id);
}