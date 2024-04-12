package LibraryMS.MS.service.BookService;

import LibraryMS.MS.model.Book.Book;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import LibraryMS.MS.repository.BookRepository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
    public Book getBookbyID(int id) {
        return bookRepository.findByID(id)
                .orElseThrow(() -> new NoSuchElementException());
    }
    @Transactional
    public void addBook(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public void deleteBookbyID(int id) {
        if (!bookRepository.existsById(String.valueOf(id))) {
            throw new NoSuchElementException();
        }
        bookRepository.deleteByID(id);
    }
    @Transactional
    public void updateBook(Book book) {
        if (book.getID() != 0 && !bookRepository.existsById(String.valueOf(book.getID()))) {
            throw new NoSuchElementException();
        }
        bookRepository.save(book);
    }

}
