package ba.unsa.etf.ri.ppis.bukvarexpress.repository;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.CategoryEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(value = "SELECT c FROM CategoryEntity c, BookCategoryEntity bc WHERE bc.categoryId = c.id AND bc.bookId=?1")
    List<CategoryEntity> findByBookId(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO book_category(book_id, category_id) VALUES (?1, ?2)", nativeQuery = true)
    void saveBookCategory(Long bookId, Long categoryId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM book_category WHERE book_id=?1", nativeQuery = true)
    void deleteAllByBookId(Long bookId);
}
