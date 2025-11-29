package hu.nje.gyakbead.controller;

import hu.nje.gyakbead.entity.Pilota;
import hu.nje.gyakbead.repository.PilotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilotak")
@RequiredArgsConstructor
public class PilotaRestController {

    private final PilotaRepository pilotaRepository;

    // 1. Összes pilóta (GET)
    @GetMapping
    public List<Pilota> osszes() {
        return pilotaRepository.findAll();
    }

    // 2. Egy pilóta ID szerint (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Pilota> egy(@PathVariable Integer id) {
        return pilotaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Új pilóta létrehozása (POST) – bárki hozzáadhat!
    @PostMapping
    public ResponseEntity<Pilota> uj(@RequestBody Pilota pilota) {
        Pilota mentett = pilotaRepository.save(pilota);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentett);
    }

    // 4. Pilóta módosítása (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Pilota> modositas(@PathVariable Integer id, @RequestBody Pilota pilota) {
        return pilotaRepository.findById(id)
                .map(letezo -> {
                    pilota.setAz(id);
                    return ResponseEntity.ok(pilotaRepository.save(pilota));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Pilóta törlése (DELETE) – bárki törölhet (csak teszteléshez!)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> torles(@PathVariable Integer id) {
        if (pilotaRepository.existsById(id)) {
            pilotaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}