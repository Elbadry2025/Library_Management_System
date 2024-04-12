package LibraryMS.MS.model.Book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Entity
@Table(name = "Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Author")
    private String Author;

    @Column(name = "Publication_Year")
    private String Publication_Year;

    @Column(name = "ISBN")
    private String ISBN;

}
