package LibraryMS.MS.controller.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatronDTO {
    private int ID;
    private String Name;
    private String Email;
    private String Phone;
}