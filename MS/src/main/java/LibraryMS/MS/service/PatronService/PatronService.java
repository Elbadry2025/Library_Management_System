package LibraryMS.MS.service.PatronService;

import LibraryMS.MS.model.Patron.Patron;
import LibraryMS.MS.repository.PatronRepository.PatronRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PatronService {
    private final PatronRepository patronRepository;
    public List<Patron> getPatrons() {
        return patronRepository.findAll();
    }
    public Patron getPatron(int ID) {
        return patronRepository.findByID(ID)
                .orElseThrow(() -> new NoSuchElementException());
    }
    @Transactional
    public void addPatron(Patron patron) {
        patronRepository.save(patron);
    }
    @Transactional
    public void updatePatron(Patron patron) {
        if (patron.getID() != 0 && !patronRepository.existsById(String.valueOf(patron.getID()))) {
            throw new NoSuchElementException();
        }
        patronRepository.save(patron);
    }
    @Transactional
    public void deletePatronbyID(int id) {
        if (!patronRepository.existsById(String.valueOf(id))) {
            throw new NoSuchElementException();
        }
        patronRepository.deleteByID(id);
    }
}
