package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.Review;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
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
    
    @PutMapping
    public ResponseEntity<Review> updateReview(@RequestBody Review newReview) {
        if (newReview.getId() == null) {
            return null;
        }
        Review review = reviewService.getReviewById(newReview.getId());
        if (review == null) {
            return null;
        }

        Review updatedReview = reviewService.updateReview(newReview);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteReview(@RequestParam Long reviewId) {
        if (reviewId == null) {
            return null;
        }
        Review review = reviewService.getReviewById(reviewId);
        if (review == null) {
            return null;
        }
        reviewService.deleteReviewById(reviewId);
        return ResponseEntity.ok("Successfully deleted a review!");
    }
}
