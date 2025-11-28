package hu.nje.gyakbead.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final JdbcTemplate jdbc;
    private final PasswordEncoder encoder;

    public AuthController(JdbcTemplate jdbc, PasswordEncoder encoder) {
        this.jdbc = jdbc;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String password2,
            Model model) {

        if (!password.equals(password2)) {
            model.addAttribute("error", "A jelszavak nem egyeznek!");
            return "register";
        }

        if (username.isBlank() || password.isBlank()) {
            model.addAttribute("error", "Minden mező kötelező!");
            return "register";
        }

        String encoded = encoder.encode(password);
        try {
            jdbc.update("INSERT INTO users (username, password, enabled) VALUES (?, ?, TRUE)", username, encoded);
            jdbc.update("INSERT INTO authorities (username, authority) VALUES (?, 'ROLE_USER')", username);
            model.addAttribute("success", "Sikeres regisztráció! Most bejelentkezhetsz.");
        } catch (Exception e) {
            model.addAttribute("error", "A felhasználónév már foglalt!");
        }
        return "register";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}
