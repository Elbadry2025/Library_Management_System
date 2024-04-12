package LibraryMS.MS.controller.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecordDTO {
    private int bookID;
    private int patronID;
    private LocalDate borrow_date;
    private LocalDate return_date;
}