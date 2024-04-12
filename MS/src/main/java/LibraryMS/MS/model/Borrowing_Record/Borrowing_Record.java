package LibraryMS.MS.model.Borrowing_Record;

import LibraryMS.MS.model.Book.Book;
import LibraryMS.MS.model.Patron.Patron;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_record")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Borrowing_Record {
    @EmbeddedId
    private record_ID recordID;

    @ManyToOne
    @JoinColumn(name = "bookID", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patronID", insertable = false, updatable = false)
    private Patron patron;

    @Column(name = "borrowed_date")
    private LocalDate borrowed_date;

    @Column(name = "return_date")
    private LocalDate return_date;
}
