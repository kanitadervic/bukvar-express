package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Book;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getBookById(Long bookId) {
        return toModel(bookRepository.getById(bookId));
    }

    public Book addBook(Book book) {
        BookEntity bookEntity = toEntity(book);

        return toModel(bookRepository.save(bookEntity));
    }

    public Book updateBook(Book book) {
        BookEntity bookEntity = toEntity(book);

        return toModel(bookRepository.save(bookEntity));
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    private BookEntity toEntity(Book book) {
        return BookEntity
                .builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .datePublished(Date.from(book.getDatePublished()))
                .stock(book.getStock())
                .build();
    }

    private Book toModel(BookEntity book) {
        return Book
                .builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .datePublished(book.getDatePublished().toInstant())
                .stock(book.getStock())
                .build();
    }
}
