package LibraryMS.MS.repository.PatronRepository;

import LibraryMS.MS.model.Patron.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron, String> {
    Optional<Patron> findByID(int ID);
    void deleteByID(int ID);

}
