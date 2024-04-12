package LibraryMS.MS.model.Borrowing_Record;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class record_ID implements Serializable {
    @Column(name = "bookID")
    private int bookID;
    @Column(name = "patronID")
    private int patronID;
}
