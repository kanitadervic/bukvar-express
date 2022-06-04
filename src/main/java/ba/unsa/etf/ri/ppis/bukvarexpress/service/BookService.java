package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.entity.CategoryEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Book;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Category;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.BookRepository;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

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
        bookEntity.setTotalReviews(0);
        bookEntity.setRating(0D);

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

    public List<Book> findByTitleOrAuthor(String inputString1){
        return bookRepository.findByNameContaining(inputString1).stream().map(this::toModel).toList();
    }

    public List<Book> findBooksByCategoryId(Long categoryId, List<Book> bookList){
        if(isNull(bookList)){
            bookList = getAllBooks();
        }
        if(isNull(categoryId)){
            return bookList;
        }

        var result = new ArrayList<Book>();
        for (var book : bookList){
            if(book.getCategoryIds().contains(categoryId)){
                result.add(book);
            }
        }
        return result;
    }

    private BookEntity toEntity(Book book) {
        return BookEntity
                .builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .datePublished(Date.from(book.getDatePublished()))
                .stock(book.getStock())
                .price(book.getPrice())
                .imageUrl(book.getImageUrl())
                .rating(book.getRating())
                .totalReviews(book.getTotalReviews())
                .description(book.getDescription())
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
                .imageUrl(book.getImageUrl())
                .price(book.getPrice())
                .rating(book.getRating())
                .totalReviews(book.getTotalReviews())
                .description(book.getDescription())
                .build();
    }

    public List<Book> getLowOnStockBooks() {
        return bookRepository.findBooksWithStock5AndBelow().stream().map(this::toModel).toList();
    }
}
