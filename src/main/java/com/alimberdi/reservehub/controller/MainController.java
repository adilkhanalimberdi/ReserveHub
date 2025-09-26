package com.alimberdi.reservehub.controller;

import com.alimberdi.reservehub.domain.dto.UserDTO;
import com.alimberdi.reservehub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserDTO user = userService.getUserByEmail(userDetails.getUsername());

        model.addAttribute("name", user.name());
        model.addAttribute("email", user.email());
        model.addAttribute("title", "Reserve Hub");

        return "index";
    }

    @GetMapping("/index")
    public String indexRedirect() {
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserDTO user = userService.getUserByEmail(userDetails.getUsername());

        model.addAttribute("name", user.name());
        model.addAttribute("email", user.email());
        return "profile";
    }

    @GetMapping("/sign-up")
    public String register(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) return "redirect:/";

        return "registration";
    }

    @GetMapping("/sign-in")
    public String login(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) return "redirect:/";

        return "login";
    }

}
