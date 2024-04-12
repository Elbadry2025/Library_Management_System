package LibraryMS.MS.controller.BorrwoingRecord;

import LibraryMS.MS.controller.DTOs.BorrowingRecordDTO;
import LibraryMS.MS.model.Borrowing_Record.Borrowing_Record;
import LibraryMS.MS.model.Borrowing_Record.record_ID;
import LibraryMS.MS.service.BookService.BookService;
import LibraryMS.MS.service.BorrowingRecordService.BorrowingRecordService;
import LibraryMS.MS.service.PatronService.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RecordController {
    private final BorrowingRecordService recordService;
    private final BookService bookService;
    private final PatronService patronService;

    @GetMapping("/borrow")
    public List<BorrowingRecordDTO> getAllRecords() {
        return recordService.getRecords().stream()
                .map(record -> new BorrowingRecordDTO(record.getBook().getID(), record.getPatron().getID(), record.getBorrowed_date(), record.getReturn_date()))
                .collect(Collectors.toList());
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public void borrowBook(@PathVariable int bookId, @PathVariable int patronId) {
        record_ID record = new record_ID(bookId, patronId);
        Borrowing_Record borrowing_record = new Borrowing_Record(record, bookService.getBookbyID(bookId), patronService.getPatron(patronId), LocalDate.now(),null);
        recordService.addRecord(borrowing_record);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public void returnBook(@PathVariable int bookId, @PathVariable int patronId) {
        record_ID record = new record_ID(bookId, patronId);
        Borrowing_Record borrowing_record = recordService.getRecord(record);
        if (borrowing_record != null) {
            borrowing_record.setReturn_date(LocalDate.now());
            recordService.updateRecord(borrowing_record);
        }
    }
}