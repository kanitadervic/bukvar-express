package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.ReviewEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Review;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookService bookService;

    public Review getReviewById(Long reviewId) {
        return toModel(reviewRepository.getById(reviewId));
    }

    public Review createReview(Review review) {
        ReviewEntity reviewEntity = toEntity(review);
        updateBookRating(review);
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

    public Integer findRatingByBookId (Long bookId){
        return reviewRepository.findRatingByBookId(bookId);
    }

    public List<Review> getAllReviews (){
        return reviewRepository.findAll().stream().map(this::toModel).toList();
    }

    private void updateBookRating (Review review){
        var book = bookService.getBookById(review.getBookId());
        book.setRating(book.getRating() + review.getRating());
        book.setTotalReviews(book.getTotalReviews() + 1);
        bookService.updateBook(book);
    }
}
