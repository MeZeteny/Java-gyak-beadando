package hu.nje.gyakbead.controller;

import hu.nje.gyakbead.entity.Uzenet;
import hu.nje.gyakbead.repository.UzenetRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KapcsolatController {

    private final UzenetRepository uzenetRepository;

    public KapcsolatController(UzenetRepository uzenetRepository) {
        this.uzenetRepository = uzenetRepository;
    }

    @GetMapping("/kapcsolat")
    public String showForm(Model model) {
        model.addAttribute("uzenet", new Uzenet());
        return "kapcsolat";
    }

    @PostMapping("/kapcsolat")
    public String sendMessage(@Valid @ModelAttribute Uzenet uzenet,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            return "kapcsolat"; // vissza az űrlapra a hibákkal
        }

        uzenetRepository.save(uzenet);
        model.addAttribute("siker", true);
        model.addAttribute("uzenet", new Uzenet()); // új üres űrlap
        return "kapcsolat";
    }
}