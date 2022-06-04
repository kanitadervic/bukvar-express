package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.Book;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @RequestMapping(value = "", params = "bookId")
    public ResponseEntity<Book> getBook(@RequestParam Long bookId) {
        Book book = bookService.getBookById(bookId);

        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();

        return ResponseEntity.ok(bookList);
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

    @GetMapping("/search")
    public ResponseEntity<List<Book>> findByTitleOrAuthor (@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String inputString){
        if(isNull(inputString)) {
            inputString = "";
        }
        var booksWithSimilarTitleOrAuthor = bookService.findByTitleOrAuthor(inputString.toLowerCase());
        return ResponseEntity.ok(bookService.findBooksByCategoryId(categoryId, booksWithSimilarTitleOrAuthor));
    }

//    @GetMapping("/searchByCategory")
//    public ResponseEntity<List<Book>> findByCategory (@RequestParam(required = false) Long categoryId){
//        System.out.println(categoryId);
//       return ResponseEntity.ok(bookService.findBooksByCategoryId(categoryId), null);
//    }

    @GetMapping("/lowonstock")
    public ResponseEntity<List<Book>> getLowOnStockBooks() {
        return ResponseEntity.ok(bookService.getLowOnStockBooks());
    }
}
