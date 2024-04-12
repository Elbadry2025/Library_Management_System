package LibraryMS.MS.BorrowingRecord;

import LibraryMS.MS.model.Borrowing_Record.Borrowing_Record;
import LibraryMS.MS.model.Borrowing_Record.record_ID;
import LibraryMS.MS.repository.RecordRepository.RecordRepository;
import LibraryMS.MS.service.BorrowingRecordService.BorrowingRecordService;
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
 * This class contains unit tests for the BorrowingRecordService class.
 */
public class ServiceTest {

    // The BorrowingRecordService instance that we are testing.
    @InjectMocks
    private BorrowingRecordService borrowingRecordService;

    // A mock RecordRepository for simulating database operations.
    @Mock
    private RecordRepository recordRepository;

    /**
     * This method is executed before each test. It initializes the mocks.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for the getRecords method of BorrowingRecordService.
     * It verifies that the method returns the correct number of records.
     */
    @Test
    public void retrievesAllRecords() {
        when(recordRepository.findAll()).thenReturn(Arrays.asList(new Borrowing_Record(), new Borrowing_Record()));
        List<Borrowing_Record> records = borrowingRecordService.getRecords();
        assertEquals(2, records.size());
    }

    /**
     * Test for the getRecord method of BorrowingRecordService.
     * It verifies that the method returns the correct record when the record exists.
     */
    @Test
    public void retrievesRecordById() {
        record_ID recordID = new record_ID();
        Borrowing_Record record = new Borrowing_Record();
        when(recordRepository.findByRecordID(recordID)).thenReturn(Optional.of(record));
        Borrowing_Record result = borrowingRecordService.getRecord(recordID);
        assertEquals(record, result);
    }

    /**
     * Test for the getRecord method of BorrowingRecordService.
     * It verifies that the method throws an exception when the record does not exist.
     */
    @Test
    public void throwsExceptionWhenRecordNotFound() {
        record_ID recordID = new record_ID();
        when(recordRepository.findByRecordID(recordID)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> borrowingRecordService.getRecord(recordID));
    }

    /**
     * Test for the addRecord method of BorrowingRecordService.
     * It verifies that the method correctly saves the record to the repository.
     */
    @Test
    public void addsRecord() {
        Borrowing_Record record = new Borrowing_Record();
        borrowingRecordService.addRecord(record);
        verify(recordRepository, times(1)).save(record);
    }

    /**
     * Test for the updateRecord method of BorrowingRecordService.
     * It verifies that the method correctly updates the record in the repository when the record exists.
     */
    @Test
    public void updatesExistingRecord() {
        record_ID recordID = new record_ID();
        Borrowing_Record record = new Borrowing_Record();
        record.setRecordID(recordID);
        when(recordRepository.existsById(recordID)).thenReturn(true);
        borrowingRecordService.updateRecord(record);
        verify(recordRepository, times(1)).save(record);
    }

    /**
     * Test for the updateRecord method of BorrowingRecordService.
     * It verifies that the method throws an exception when the record does not exist.
     */
    @Test
    public void throwsExceptionWhenUpdatingNonExistingRecord() {
        record_ID recordID = new record_ID();
        Borrowing_Record record = new Borrowing_Record();
        record.setRecordID(recordID);
        when(recordRepository.existsById(recordID)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> borrowingRecordService.updateRecord(record));
    }
}