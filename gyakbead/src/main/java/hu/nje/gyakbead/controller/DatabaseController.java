package hu.nje.gyakbead.controller;

import hu.nje.gyakbead.repository.EredmenyRepository;
import hu.nje.gyakbead.repository.GpRepository;
import hu.nje.gyakbead.repository.PilotaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatabaseController {
    private final PilotaRepository pilotaRepo;
    private final GpRepository gpRepo;
    private final EredmenyRepository eredmenyRepo;

    public DatabaseController(PilotaRepository pilotaRepo, GpRepository gpRepo, EredmenyRepository eredmenyRepo) {
        this.pilotaRepo = pilotaRepo;
        this.gpRepo = gpRepo;
        this.eredmenyRepo = eredmenyRepo;
    }

    @GetMapping("/adatbazis")
    public String adatbazis(Model model) {
        model.addAttribute("pilotak", pilotaRepo.findAll());
        model.addAttribute("gp", gpRepo.findAll());
        model.addAttribute("eredmenyek", eredmenyRepo.findAll());
        return "adatbazis";
    }
}
