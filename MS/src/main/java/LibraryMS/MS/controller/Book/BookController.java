package LibraryMS.MS.controller.Book;

import LibraryMS.MS.service.BookService.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import LibraryMS.MS.model.Book.Book;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getBookbyID(id);
    }
    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }
    @PutMapping("")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBookbyID(id);
    }


}
