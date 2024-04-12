package LibraryMS.MS.Book;

import LibraryMS.MS.model.Book.Book;
import LibraryMS.MS.service.BookService.BookService;
import LibraryMS.MS.controller.Book.BookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the BookController class.
 */
public class ControllerTest {

    // The BookController instance that we are testing.
    @InjectMocks
    private BookController bookController;

    // A mock BookService for simulating service operations.
    @Mock
    private BookService bookService;

    /**
     * This method is executed before each test. It initializes the mocks.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for the getAllBooks method of BookController.
     * It verifies that the method returns the correct number of books.
     */
    @Test
    public void retrievesAllBooks() {
        when(bookService.getBooks()).thenReturn(Arrays.asList(new Book(), new Book()));
        List<Book> books = bookController.getAllBooks();
        assertEquals(2, books.size());
    }

    /**
     * Test for the getBook method of BookController.
     * It verifies that the method returns the correct book when the book exists.
     */
    @Test
    public void retrievesBookById() {
        Book book = new Book();
        when(bookService.getBookbyID(1)).thenReturn(book);
        Book result = bookController.getBook(1);
        assertEquals(book, result);
    }

    /**
     * Test for the getBook method of BookController.
     * It verifies that the method throws an exception when the book does not exist.
     */
    @Test
    public void throwsExceptionWhenBookNotFound() {
        when(bookService.getBookbyID(1)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> bookController.getBook(1));
    }

    /**
     * Test for the addBook method of BookController.
     * It verifies that the method correctly adds the book to the service.
     */
    @Test
    public void addsBook() {
        Book book = new Book();
        bookController.addBook(book);
        verify(bookService, times(1)).addBook(book);
    }

    /**
     * Test for the updateBook method of BookController.
     * It verifies that the method correctly updates the book in the service.
     */
    @Test
    public void updatesExistingBook() {
        Book book = new Book();
        bookController.updateBook(book);
        verify(bookService, times(1)).updateBook(book);
    }

    /**
     * Test for the deleteBook method of BookController.
     * It verifies that the method correctly deletes the book from the service.
     */
    @Test
    public void deletesExistingBook() {
        bookController.deleteBook(1);
        verify(bookService, times(1)).deleteBookbyID(1);
    }
}