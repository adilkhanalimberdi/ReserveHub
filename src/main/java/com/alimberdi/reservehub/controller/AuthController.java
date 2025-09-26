package com.alimberdi.reservehub.controller;

import com.alimberdi.reservehub.domain.dto.UserRegistrationDTO;
import com.alimberdi.reservehub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public String signUp(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ) {
        UserRegistrationDTO dto = new UserRegistrationDTO(name, email, password);
        System.out.println(dto);
        userService.createUser(dto);
        return "redirect:/sign-in?registered=true";
    }

}
