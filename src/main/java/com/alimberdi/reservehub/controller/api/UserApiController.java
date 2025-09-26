package com.alimberdi.reservehub.controller.api;


import com.alimberdi.reservehub.domain.dto.UserDTO;
import com.alimberdi.reservehub.domain.dto.UserRegistrationDTO;
import com.alimberdi.reservehub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserApiController {

    private final UserService userService;

    // Getting user by id
    @GetMapping("/{id}")
    public UserDTO getUsers(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Getting all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Creating a new user
    @PostMapping
    public UserDTO createUser(@RequestBody UserRegistrationDTO dto) {
        return userService.createUser(dto);
    }

    // Updating user by id
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    // Deleting user by id
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
