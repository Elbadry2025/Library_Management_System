package LibraryMS.MS.Patron;

import LibraryMS.MS.controller.DTOs.PatronDTO;
import LibraryMS.MS.controller.Patron.PatronController;
import LibraryMS.MS.model.Patron.Patron;
import LibraryMS.MS.service.PatronService.PatronService;
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
 * This class contains unit tests for the PatronController class.
 */
public class ControllerTest {

    // The PatronController instance that we are testing.
    @InjectMocks
    private PatronController patronController;

    // A mock PatronService for simulating service operations.
    @Mock
    private PatronService patronService;

    /**
     * This method is executed before each test. It initializes the mocks.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for the getAllPatrons method of PatronController.
     * It verifies that the method returns the correct number of patrons.
     */
    @Test
    public void retrievesAllPatrons() {
        when(patronService.getPatrons()).thenReturn(Arrays.asList(new Patron(), new Patron()));
        List<PatronDTO> patrons = patronController.getAllPatrons();
        assertEquals(2, patrons.size());
    }

    /**
     * Test for the getPatron method of PatronController.
     * It verifies that the method returns the correct patron when the patron exists.
     */
    @Test
    public void retrievesPatronById() {
        Patron patron = new Patron();
        when(patronService.getPatron(1)).thenReturn(patron);
        PatronDTO result = patronController.getPatron(1);
        assertEquals(patron.getID(), result.getID());
        assertEquals(patron.getName(), result.getName());
        assertEquals(patron.getEmail(), result.getEmail());
        assertEquals(patron.getPhone(), result.getPhone());
    }

    /**
     * Test for the getPatron method of PatronController.
     * It verifies that the method throws an exception when the patron does not exist.
     */
    @Test
    public void throwsExceptionWhenPatronNotFound() {
        when(patronService.getPatron(1)).thenThrow(new NoSuchElementException("Patron not found"));
        assertThrows(NoSuchElementException.class, () -> patronController.getPatron(1));
    }

    /**
     * Test for the addPatron method of PatronController.
     * It verifies that the method correctly adds the patron to the service.
     */
    @Test
    public void addsPatron() {
        PatronDTO patronDTO = new PatronDTO(1, "Name", "Email", "Phone");
        patronController.addPatron(patronDTO);
        verify(patronService, times(1)).addPatron(any(Patron.class));
    }

    /**
     * Test for the updatePatron method of PatronController.
     * It verifies that the method correctly updates the patron in the service.
     */
    @Test
    public void updatesExistingPatron() {
        PatronDTO patronDTO = new PatronDTO(1, "Name", "Email", "Phone");
        patronController.updatePatron(patronDTO);
        verify(patronService, times(1)).updatePatron(any(Patron.class));
    }

    /**
     * Test for the deletePatron method of PatronController.
     * It verifies that the method correctly deletes the patron from the service.
     */
    @Test
    public void deletesExistingPatron() {
        patronController.deletePatron(1);
        verify(patronService, times(1)).deletePatronbyID(1);
    }
}