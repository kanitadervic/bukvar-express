package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.Review;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Review> getReviewById(@RequestParam Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);

        return ResponseEntity.ok(review);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review newReview) {
        Review review = reviewService.createReview(newReview);

        return ResponseEntity.ok(review);
    }
}
