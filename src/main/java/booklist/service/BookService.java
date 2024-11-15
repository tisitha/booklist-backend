package booklist.service;

import booklist.repo.BookRepository;
import booklist.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooksByUser_id(Long userId){
            return bookRepository.findByUserId(userId);
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
    }

    public Book addBook(Book book,Long userId){
        book.setUserId(userId);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id,Book updatedBook){
        Book book = getBookById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setPublish_year(updatedBook.getPublish_year());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
