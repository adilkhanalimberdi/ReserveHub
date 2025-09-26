package com.alimberdi.reservehub.services;

import com.alimberdi.reservehub.domain.dto.UserDTO;
import com.alimberdi.reservehub.domain.dto.UserRegistrationDTO;
import com.alimberdi.reservehub.domain.entity.User;
import com.alimberdi.reservehub.mapper.UserMapper;
import com.alimberdi.reservehub.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserDTO getUserById(Long id) {
        return UserMapper.toDTO(userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserDTO getUserByEmail(String email) {
        return UserMapper.toDTO(userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public List<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserDTO createUser(UserRegistrationDTO dto) {
        User user = UserMapper.fromRegistrationDTO(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        return UserMapper.toDTO(userRepo.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.name());
        user.setEmail(dto.email());

        return UserMapper.toDTO(userRepo.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }
}
