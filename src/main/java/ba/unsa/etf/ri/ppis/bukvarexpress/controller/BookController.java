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
}
