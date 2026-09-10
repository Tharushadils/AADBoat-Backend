package lk.ijse.boat_reservation.service.impl;

import lk.ijse.boat_reservation.dto.ReviewDTO;
import lk.ijse.boat_reservation.entity.Boat;
import lk.ijse.boat_reservation.entity.Review;
import lk.ijse.boat_reservation.entity.User;
import lk.ijse.boat_reservation.exception.CustomException;
import lk.ijse.boat_reservation.repository.BoatRepository;
import lk.ijse.boat_reservation.repository.ReviewRepository;
import lk.ijse.boat_reservation.repository.UserRepository;
import lk.ijse.boat_reservation.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BoatRepository boatRepository;

    public ReviewServiceImpl(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            BoatRepository boatRepository) {

        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.boatRepository = boatRepository;
    }

    @Override
    @Transactional
    public ReviewDTO addReview(ReviewDTO dto) {
        log.info("Execute method addReview()");

        try {
            if (dto == null) {
                throw new CustomException(
                        400,
                        "Review data cannot be null!"
                );
            }

            if (dto.getUserId() == null || dto.getUserId() <= 0) {
                throw new CustomException(
                        400,
                        "Valid User ID is required!"
                );
            }

            if (dto.getBoatId() == null || dto.getBoatId() <= 0) {
                throw new CustomException(
                        400,
                        "Valid Boat ID is required!"
                );
            }

            Optional<User> userOptional =
                    userRepository.findById(dto.getUserId());

            if (userOptional.isEmpty()) {
                log.error(
                        "User with id {} does not exist",
                        dto.getUserId()
                );

                throw new CustomException(
                        404,
                        "User not found with id: " + dto.getUserId()
                );
            }

            Optional<Boat> boatOptional =
                    boatRepository.findById(dto.getBoatId());

            if (boatOptional.isEmpty()) {
                log.error(
                        "Boat with id {} does not exist",
                        dto.getBoatId()
                );

                throw new CustomException(
                        404,
                        "Boat not found with id: " + dto.getBoatId()
                );
            }

            Review review = new Review();

            review.setRatingStars(dto.getRatingStars());
            review.setComment(dto.getComment());
            review.setReviewDate(LocalDate.now());
            review.setUser(userOptional.get());
            review.setBoat(boatOptional.get());

            Review savedReview =
                    reviewRepository.save(review);

            ReviewDTO responseDTO =
                    new ReviewDTO();

            responseDTO.setReviewId(
                    savedReview.getReviewId()
            );

            responseDTO.setRatingStars(
                    savedReview.getRatingStars()
            );

            responseDTO.setComment(
                    savedReview.getComment()
            );

            responseDTO.setReviewDate(
                    savedReview.getReviewDate()
            );

            if (savedReview.getUser() != null) {
                responseDTO.setUserId(
                        savedReview.getUser().getUserId()
                );

                responseDTO.setUserName(
                        savedReview.getUser().getFullName()
                );
            }

            if (savedReview.getBoat() != null) {
                responseDTO.setBoatId(
                        savedReview.getBoat().getBoatId()
                );

                responseDTO.setBoatName(
                        savedReview.getBoat().getBoatName()
                );
            }

            log.info("Review added successfully");

            return responseDTO;

        } catch (Exception e) {
            log.error(
                    "Error occurred while adding review: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewDTO getReviewById(Long id) {
        log.info("Execute method getReviewById()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Review ID: " + id
                );
            }

            Optional<Review> reviewOptional =
                    reviewRepository.findById(id);

            if (reviewOptional.isEmpty()) {
                log.error(
                        "Review with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Review not found with id: " + id
                );
            }

            Review review = reviewOptional.get();

            ReviewDTO dto = new ReviewDTO();

            dto.setReviewId(
                    review.getReviewId()
            );

            dto.setRatingStars(
                    review.getRatingStars()
            );

            dto.setComment(
                    review.getComment()
            );

            dto.setReviewDate(
                    review.getReviewDate()
            );

            if (review.getUser() != null) {
                dto.setUserId(
                        review.getUser().getUserId()
                );

                dto.setUserName(
                        review.getUser().getFullName()
                );
            }

            if (review.getBoat() != null) {
                dto.setBoatId(
                        review.getBoat().getBoatId()
                );

                dto.setBoatName(
                        review.getBoat().getBoatName()
                );
            }

            return dto;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching review: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviewsByBoat(Long boatId) {
        log.info("Execute method getReviewsByBoat()");

        try {
            if (boatId == null || boatId <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Boat ID: " + boatId
                );
            }

            List<Review> reviews =
                    reviewRepository.findByBoatBoatId(boatId);

            List<ReviewDTO> responseList =
                    new ArrayList<>();

            for (Review review : reviews) {

                ReviewDTO dto = new ReviewDTO();

                dto.setReviewId(
                        review.getReviewId()
                );

                dto.setRatingStars(
                        review.getRatingStars()
                );

                dto.setComment(
                        review.getComment()
                );

                dto.setReviewDate(
                        review.getReviewDate()
                );

                if (review.getUser() != null) {
                    dto.setUserId(
                            review.getUser().getUserId()
                    );

                    dto.setUserName(
                            review.getUser().getFullName()
                    );
                }

                if (review.getBoat() != null) {
                    dto.setBoatId(
                            review.getBoat().getBoatId()
                    );

                    dto.setBoatName(
                            review.getBoat().getBoatName()
                    );
                }

                responseList.add(dto);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching reviews by boat: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getAllReviews() {
        log.info("Execute method getAllReviews()");

        try {
            List<Review> reviews =
                    reviewRepository.findAll();

            List<ReviewDTO> responseList =
                    new ArrayList<>();

            for (Review review : reviews) {

                ReviewDTO dto = new ReviewDTO();

                dto.setReviewId(
                        review.getReviewId()
                );

                dto.setRatingStars(
                        review.getRatingStars()
                );

                dto.setComment(
                        review.getComment()
                );

                dto.setReviewDate(
                        review.getReviewDate()
                );

                if (review.getUser() != null) {
                    dto.setUserId(
                            review.getUser().getUserId()
                    );

                    dto.setUserName(
                            review.getUser().getFullName()
                    );
                }

                if (review.getBoat() != null) {
                    dto.setBoatId(
                            review.getBoat().getBoatId()
                    );

                    dto.setBoatName(
                            review.getBoat().getBoatName()
                    );
                }

                responseList.add(dto);
            }

            return responseList;

        } catch (Exception e) {
            log.error(
                    "Error occurred while fetching all reviews: {}",
                    e.getMessage()
            );
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        log.info("Execute method deleteReview()");

        try {
            if (id == null || id <= 0) {
                throw new CustomException(
                        400,
                        "Invalid Review ID: " + id
                );
            }

            Optional<Review> reviewOptional =
                    reviewRepository.findById(id);

            if (reviewOptional.isEmpty()) {
                log.error(
                        "Review with id {} does not exist",
                        id
                );

                throw new CustomException(
                        404,
                        "Review not found with id: " + id
                );
            }

            reviewRepository.deleteById(id);

            log.info(
                    "Review deleted successfully with id: {}",
                    id
            );

        } catch (Exception e) {
            log.error(
                    "Error occurred while deleting review: {}",
                    e.getMessage()
            );
            throw e;
        }
    }
}