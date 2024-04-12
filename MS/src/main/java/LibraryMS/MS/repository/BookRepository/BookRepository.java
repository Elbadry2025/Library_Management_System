package LibraryMS.MS.repository.BookRepository;

import LibraryMS.MS.model.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByID(int id);
    void deleteByID(int id);
}
