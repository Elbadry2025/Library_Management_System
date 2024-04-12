package LibraryMS.MS.repository.RecordRepository;

import LibraryMS.MS.model.Borrowing_Record.Borrowing_Record;
import LibraryMS.MS.model.Borrowing_Record.record_ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Borrowing_Record, record_ID> {
    Optional<Borrowing_Record> findByRecordID(record_ID recordID);

}
