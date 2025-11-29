package hu.nje.gyakbead.controller;

import hu.nje.gyakbead.entity.Pilota;
import hu.nje.gyakbead.repository.PilotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crud/pilota")
@RequiredArgsConstructor
public class CRUDController {
    private final PilotaRepository pilotaRepository;

    // LISTÁZÁS
    @GetMapping
    public String lista(Model model) {
        model.addAttribute("pilotak", pilotaRepository.findAll());
        return "crud/pilota-lista";
    }

    // ÚJ FELVÉTELE ŰRLAP
    @GetMapping("/uj")
    public String ujForm(Model model) {
        model.addAttribute("pilota", new Pilota());
        return "crud/pilota-form";
    }

    // MENTÉS (új)
    @PostMapping("/uj")
    public String ujMentes(@ModelAttribute Pilota pilota) {
        pilotaRepository.save(pilota);
        return "redirect:/crud/pilota";
    }

    // SZERKESZTÉS ŰRLAP
    @GetMapping("/szerkesztes/{id}")
    public String szerkesztesForm(@PathVariable Integer id, Model model) {
        Pilota pilota = pilotaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nincs ilyen pilóta"));
        model.addAttribute("pilota", pilota);
        return "crud/pilota-form";
    }

    // MÓDOSÍTÁS MENTÉSE
    @PostMapping("/szerkesztes/{id}")
    public String szerkesztesMentes(@PathVariable Integer id, @ModelAttribute Pilota pilota) {
        pilota.setAz(id);
        pilotaRepository.save(pilota);
        return "redirect:/crud/pilota";
    }

    // TÖRLÉS
    @GetMapping("/torles/{id}")
    public String torles(@PathVariable Integer id) {
        pilotaRepository.deleteById(id);
        return "redirect:/crud/pilota";
    }
}
