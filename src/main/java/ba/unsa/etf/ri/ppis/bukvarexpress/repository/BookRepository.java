package ba.unsa.etf.ri.ppis.bukvarexpress.repository;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
