package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.ReviewEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Review;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review getReviewById(Long reviewId) {
        return toModel(reviewRepository.getById(reviewId));
    }

    public Review createReview(Review review) {
        ReviewEntity reviewEntity = toEntity(review);

        return toModel(reviewRepository.save(reviewEntity));
    }

    public Review updateReview(Review review) {
        ReviewEntity reviewEntity = toEntity(review);

        return toModel(reviewRepository.save(reviewEntity));
    }

    public void deleteReviewById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    private ReviewEntity toEntity(Review review) {
        return ReviewEntity
                .builder()
                .id(review.getId())
                .userId(review.getUserId())
                .bookId(review.getBookId())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }

    private Review toModel(ReviewEntity review) {
        return Review
                .builder()
                .id(review.getId())
                .userId(review.getUserId())
                .bookId(review.getBookId())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }
}
