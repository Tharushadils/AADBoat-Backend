package lk.ijse.boat_reservation.controller;

import lk.ijse.boat_reservation.constant.CommonResponse;
import lk.ijse.boat_reservation.dto.ReviewDTO;
import lk.ijse.boat_reservation.service.ReviewService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.boat_reservation.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.boat_reservation.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse addReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.addReview(reviewDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteReview(@PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getReviewById(@PathVariable long reviewId) {
        ReviewDTO review = reviewService.getReviewById(reviewId);
        return new CommonResponse(OPERATION_SUCCESS, review, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/boat/{boatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getReviewsByBoat(@PathVariable long boatId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByBoat(boatId);
        return new CommonResponse(OPERATION_SUCCESS, reviews, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return new CommonResponse(OPERATION_SUCCESS, reviews, SUCCESS_MESSAGE);
    }

    //update krnna one nm put ek dnna puluwn passe.
}