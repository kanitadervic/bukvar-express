package ba.unsa.etf.ri.ppis.bukvarexpress.repository;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    public Integer findRatingByBookId(Long bookId);
}
