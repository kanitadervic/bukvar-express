package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.ReviewEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Review;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.User;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;

    public Review getReviewById(Long reviewId) {
        return toModel(reviewRepository.getById(reviewId));
    }

    public Review createReview(Review review) {
        ReviewEntity reviewEntity = toEntity(review);

        return toModel(reviewRepository.save(reviewEntity));
    }

    private ReviewEntity toEntity(Review review) {
        return ReviewEntity
                .builder()
                .userId(review.getUser().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }

    private Review toModel(ReviewEntity review) {
        User user = userService.getUserById(review.getUserId());
        return Review
                .builder()
                .user(user)
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }
}
