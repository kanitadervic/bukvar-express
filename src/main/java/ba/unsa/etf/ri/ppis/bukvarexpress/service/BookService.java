package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.entity.CategoryEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Book;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Category;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.BookRepository;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public Book getBookById(Long bookId) {
        return toModel(bookRepository.getById(bookId));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().map(this::toModel).toList();
    }

    public Book addBook(Book book) {
        BookEntity bookEntity = toEntity(book);

        Book savedBook = toModel(bookRepository.save(bookEntity));
        if (book.getCategoryIds() != null) {
            book.getCategoryIds().forEach(
                    categoryId -> categoryRepository.saveBookCategory(savedBook.getId(), categoryId)
            );
        }
        return getBookById(savedBook.getId());
    }

    public Book updateBook(Book book) {
        BookEntity bookEntity = toEntity(book);
        categoryRepository.deleteAllByBookId(book.getId());
        Book savedBook = toModel(bookRepository.save(bookEntity));
        if (book.getCategoryIds() != null) {
            book.getCategoryIds().forEach(
                    categoryId -> categoryRepository.saveBookCategory(savedBook.getId(), categoryId)
            );
        }
        return getBookById(savedBook.getId());
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
        List<Long> categoryList = categoryRepository.findByBookId(book.getId().intValue()).stream()
                .map(CategoryEntity::getId)
                .toList();
        return Book
                .builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .datePublished(book.getDatePublished().toInstant())
                .stock(book.getStock())
                .categoryIds(categoryList)
                .build();
    }
}
