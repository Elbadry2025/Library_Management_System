package LibraryMS.MS.Book;



import LibraryMS.MS.model.Book.Book;
import LibraryMS.MS.repository.BookRepository.BookRepository;
import LibraryMS.MS.service.BookService.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the BookService class.
 */
public class ServiceTest {

    // The BookService instance that we are testing.
    @InjectMocks
    private BookService bookService;

    // A mock BookRepository for simulating database operations.
    @Mock
    private BookRepository bookRepository;

    /**
     * This method is executed before each test. It initializes the mocks.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for the getBooks method of BookService.
     * It verifies that the method returns the correct number of books.
     */
    @Test
    public void testGetBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book(), new Book()));
        List<Book> books = bookService.getBooks();
        assertEquals(2, books.size());
    }

    /**
     * Test for the getBookbyID method of BookService.
     * It verifies that the method returns the correct book when the book exists.
     */
    @Test
    public void testGetBookById() {
        Book book = new Book();
        when(bookRepository.findByID(1)).thenReturn(Optional.of(book));
        Book result = bookService.getBookbyID(1);
        assertEquals(book, result);
    }

    /**
     * Test for the getBookbyID method of BookService.
     * It verifies that the method throws an exception when the book does not exist.
     */
    @Test
    public void testGetBookByIdNotFound() {
        when(bookRepository.findByID(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> bookService.getBookbyID(1));
    }

    /**
     * Test for the addBook method of BookService.
     * It verifies that the method correctly saves the book to the repository.
     */
    @Test
    public void testAddBook() {
        Book book = new Book();
        bookService.addBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    /**
     * Test for the deleteBookbyID method of BookService.
     * It verifies that the method correctly deletes the book from the repository when the book exists.
     */
    @Test
    public void testDeleteBookById() {
        when(bookRepository.existsById(String.valueOf(1))).thenReturn(true);
        bookService.deleteBookbyID(1);
        verify(bookRepository, times(1)).deleteByID(1);
    }

    /**
     * Test for the deleteBookbyID method of BookService.
     * It verifies that the method throws an exception when the book does not exist.
     */
    @Test
    public void testDeleteBookByIdNotFound() {
        when(bookRepository.existsById(String.valueOf(1))).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> bookService.deleteBookbyID(1));
    }

    /**
     * Test for the updateBook method of BookService.
     * It verifies that the method correctly updates the book in the repository when the book exists.
     */
    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setID(1);
        when(bookRepository.existsById(String.valueOf(1))).thenReturn(true);
        bookService.updateBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    /**
     * Test for the updateBook method of BookService.
     * It verifies that the method throws an exception when the book does not exist.
     */
    @Test
    public void testUpdateBookNotFound() {
        Book book = new Book();
        book.setID(1);
        when(bookRepository.existsById(String.valueOf(1))).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> bookService.updateBook(book));
    }
}