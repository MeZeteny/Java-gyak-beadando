package hu.nje.gyakbead.controller;

import hu.nje.gyakbead.entity.Eredmeny;
import hu.nje.gyakbead.entity.Pilota;
import hu.nje.gyakbead.repository.EredmenyRepository;
import hu.nje.gyakbead.repository.GpRepository;
import hu.nje.gyakbead.repository.PilotaRepository;
import hu.nje.gyakbead.repository.UzenetRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class DatabaseController {
    private final PilotaRepository pilotaRepo;
    private final GpRepository gpRepo;
    private final EredmenyRepository eredmenyRepo;
    private final UzenetRepository uzenetRepo;

    public DatabaseController(PilotaRepository pilotaRepo, GpRepository gpRepo, EredmenyRepository eredmenyRepo, UzenetRepository uzenetRepo) {
        this.pilotaRepo = pilotaRepo;
        this.gpRepo = gpRepo;
        this.eredmenyRepo = eredmenyRepo;
        this.uzenetRepo = uzenetRepo;
    }

    @GetMapping("/adatbazis")
    public String adatbazis(Model model) {
        model.addAttribute("pilotak", pilotaRepo.findAll());
        model.addAttribute("gp", gpRepo.findAll());
        model.addAttribute("eredmenyek", eredmenyRepo.findAll());
        return "adatbazis";
    }

    @GetMapping("/diagram")
    public String diagram(Model model) {
        Map<String, Long> nemzetStat = pilotaRepo.findAll().stream()
                .filter(p -> p.getNemzet() != null && !p.getNemzet().trim().isEmpty())
                .collect(Collectors.groupingBy(
                        Pilota::getNemzet,
                        Collectors.counting()
                ));


        List<String> nemzetek = nemzetStat.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();

        List<Long> szamok = nemzetStat.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getValue)
                .toList();

        model.addAttribute("nemzetek", nemzetek);
        model.addAttribute("szamok", szamok);
        return "diagram";
    }

    @GetMapping("/api-docs")
    public String apiDocs() {
        return "api-docs";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model, Authentication auth) {
        // Statisztikák az adatbázisból
        long pilotakSzama = pilotaRepo.count();
        long gpSzama = gpRepo.count();
        long uzenetekSzama = uzenetRepo.count();
        long eredmenyekSzama = eredmenyRepo.count();

        model.addAttribute("pilotakSzama", pilotakSzama);
        model.addAttribute("gpSzama", gpSzama);
        model.addAttribute("uzenetekSzama", uzenetekSzama);
        model.addAttribute("eredmenyekSzama", eredmenyekSzama);
        model.addAttribute("adminNev", auth.getName());

        return "admin/dashboard";
    }
}
