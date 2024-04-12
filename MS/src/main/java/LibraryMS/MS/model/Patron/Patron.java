package LibraryMS.MS.model.Patron;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Patron")
public class Patron {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "Name")
    @NonNull
    private String Name;

    @Column(name = "Email", unique = true)
    private String Email;

    @Column(name = "Phone")
    private String Phone;

    @Column(name = "Password")
    @NonNull
    private String Password;
}
