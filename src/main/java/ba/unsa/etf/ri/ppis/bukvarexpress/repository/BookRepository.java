package ba.unsa.etf.ri.ppis.bukvarexpress.repository;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
//    public List<Book> findByNameContainingOrAuthorContaining (String inputString1, String inputString2);
    @Query("SELECT b FROM BookEntity b WHERE LOWER(b.name) LIKE %:inputString1% OR LOWER(b.author) LIKE %:inputString1%")
    public List<BookEntity> findByNameContaining(@Param("inputString1") String inputString1);

    @Query("SELECT b FROM BookEntity b WHERE b.stock <= 5")
    public List<BookEntity> findBooksWithStock5AndBelow();

    @Query("SELECT b FROM BookEntity b WHERE b.totalReviews != 0 AND b.rating/b.totalReviews >= 4.5")
    public List<BookEntity> findBooksWithHighReviewAverage();

    @Query("SELECT b FROM BookEntity b WHERE b.rating != 0.0 AND b.totalReviews != 0.0 AND b.rating/b.totalReviews <= 2.0")
    public List<BookEntity> findBooksWithLowReviewAverage();
}
