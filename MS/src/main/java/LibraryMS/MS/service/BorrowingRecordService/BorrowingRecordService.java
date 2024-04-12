package LibraryMS.MS.service.BorrowingRecordService;

import LibraryMS.MS.model.Borrowing_Record.Borrowing_Record;
import LibraryMS.MS.model.Borrowing_Record.record_ID;
import LibraryMS.MS.repository.RecordRepository.RecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BorrowingRecordService {
    private final RecordRepository recordRepository;
    public List<Borrowing_Record> getRecords() {
        return recordRepository.findAll();
    }
    public Borrowing_Record getRecord(record_ID recordID) {
        return recordRepository.findByRecordID(recordID)
                .orElseThrow(() -> new NoSuchElementException());
    }
    @Transactional
    public void addRecord(Borrowing_Record record) {
        recordRepository.save(record);
    }
    @Transactional
    public void updateRecord(Borrowing_Record record) {
        if (!recordRepository.existsById(record.getRecordID())) {
            throw new NoSuchElementException();
        }
        recordRepository.save(record);
    }
}
