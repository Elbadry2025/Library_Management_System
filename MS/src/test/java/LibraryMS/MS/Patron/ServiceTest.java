package LibraryMS.MS.Patron;

import LibraryMS.MS.model.Patron.Patron;
import LibraryMS.MS.repository.PatronRepository.PatronRepository;
import LibraryMS.MS.service.PatronService.PatronService;
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
 * This class contains unit tests for the PatronService class.
 */
public class ServiceTest {

    // The PatronService instance that we are testing.
    @InjectMocks
    private PatronService patronService;

    // A mock PatronRepository for simulating database operations.
    @Mock
    private PatronRepository patronRepository;

    /**
     * This method is executed before each test. It initializes the mocks.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for the getPatrons method of PatronService.
     * It verifies that the method returns the correct number of patrons.
     */
    @Test
    public void retrievesAllPatrons() {
        when(patronRepository.findAll()).thenReturn(Arrays.asList(new Patron(), new Patron()));
        List<Patron> patrons = patronService.getPatrons();
        assertEquals(2, patrons.size());
    }

    /**
     * Test for the getPatron method of PatronService.
     * It verifies that the method returns the correct patron when the patron exists.
     */
    @Test
    public void retrievesPatronById() {
        Patron patron = new Patron();
        when(patronRepository.findByID(1)).thenReturn(Optional.of(patron));
        Patron result = patronService.getPatron(1);
        assertEquals(patron, result);
    }

    /**
     * Test for the getPatron method of PatronService.
     * It verifies that the method throws an exception when the patron does not exist.
     */
    @Test
    public void throwsExceptionWhenPatronNotFound() {
        when(patronRepository.findByID(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> patronService.getPatron(1));
    }

    /**
     * Test for the addPatron method of PatronService.
     * It verifies that the method correctly saves the patron to the repository.
     */
    @Test
    public void addsPatron() {
        Patron patron = new Patron();
        patronService.addPatron(patron);
        verify(patronRepository, times(1)).save(patron);
    }

    /**
     * Test for the updatePatron method of PatronService.
     * It verifies that the method correctly updates the patron in the repository when the patron exists.
     */
    @Test
    public void updatesExistingPatron() {
        Patron patron = new Patron();
        patron.setID(1);
        when(patronRepository.existsById(String.valueOf(1))).thenReturn(true);
        patronService.updatePatron(patron);
        verify(patronRepository, times(1)).save(patron);
    }

    /**
     * Test for the updatePatron method of PatronService.
     * It verifies that the method throws an exception when the patron does not exist.
     */
    @Test
    public void throwsExceptionWhenUpdatingNonExistingPatron() {
        Patron patron = new Patron();
        patron.setID(1);
        when(patronRepository.existsById(String.valueOf(1))).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> patronService.updatePatron(patron));
    }

    /**
     * Test for the deletePatronbyID method of PatronService.
     * It verifies that the method correctly deletes the patron from the repository when the patron exists.
     */
    @Test
    public void deletesExistingPatron() {
        when(patronRepository.existsById(String.valueOf(1))).thenReturn(true);
        patronService.deletePatronbyID(1);
        verify(patronRepository, times(1)).deleteByID(1);
    }

    /**
     * Test for the deletePatronbyID method of PatronService.
     * It verifies that the method throws an exception when the patron does not exist.
     */
    @Test
    public void throwsExceptionWhenDeletingNonExistingPatron() {
        when(patronRepository.existsById(String.valueOf(1))).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> patronService.deletePatronbyID(1));
    }
}