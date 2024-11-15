package booklist.controller;

import booklist.service.BookService;
import booklist.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{userId}/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooksOfUser(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(bookService.getAllBooksByUser_id(userId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@PathVariable("userId") Long userId,@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book,userId),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id,@RequestBody Book book){
        return new ResponseEntity<>(bookService.updateBook(id,book),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
