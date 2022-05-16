package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.Book;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Book> getBook(@RequestParam Long bookId) {
        Book book = bookService.getBookById(bookId);

        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        Book book = bookService.addBook(newBook);

        return ResponseEntity.ok(book);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book newBook) {
        if (newBook.getId() == null) {
            return null;
        }
        Book book = bookService.getBookById(newBook.getId());
        if (book == null) {
            return null;
        }

        Book updatedBook = bookService.updateBook(newBook);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBook(@RequestParam Long bookId) {
        if (bookId == null) {
            return null;
        }
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            return null;
        }
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Successfully deleted a book!");
    }
}
