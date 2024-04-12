package LibraryMS.MS.controller.Patron;

import LibraryMS.MS.model.Patron.Patron;
import LibraryMS.MS.controller.DTOs.PatronDTO;
import LibraryMS.MS.service.PatronService.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronService patronService;

    @GetMapping("")
    public List<PatronDTO> getAllPatrons() {
        return patronService.getPatrons().stream()
                .map(patron -> new PatronDTO(patron.getID(), patron.getName(), patron.getEmail(), patron.getPhone()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PatronDTO getPatron(@PathVariable int id) {
        Patron patron = patronService.getPatron(id);
        return new PatronDTO(patron.getID(), patron.getName(), patron.getEmail(), patron.getPhone());
    }

    @PostMapping("")
    public void addPatron(@RequestBody PatronDTO patronDTO) {
        Patron patron = new Patron(patronDTO.getID(), patronDTO.getName(), patronDTO.getEmail(), patronDTO.getPhone(), "");
        patronService.addPatron(patron);
    }

    @PutMapping("")
    public void updatePatron(@RequestBody PatronDTO patronDTO) {
        Patron patron = new Patron(patronDTO.getID(), patronDTO.getName(), patronDTO.getEmail(), patronDTO.getPhone(), "");
        patronService.updatePatron(patron);
    }

    @DeleteMapping("/{id}")
    public void deletePatron(@PathVariable int id) {
        patronService.deletePatronbyID(id);
    }
}