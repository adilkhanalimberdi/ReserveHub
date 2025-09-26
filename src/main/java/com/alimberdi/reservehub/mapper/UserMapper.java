package com.alimberdi.reservehub.mapper;

import com.alimberdi.reservehub.domain.dto.UserDTO;
import com.alimberdi.reservehub.domain.dto.UserRegistrationDTO;
import com.alimberdi.reservehub.domain.entity.User;
import com.alimberdi.reservehub.domain.enums.UserRole;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public static User fromRegistrationDTO(UserRegistrationDTO dto) {
        return new User(
                null,
                dto.name(),
                dto.email(),
                dto.password(),
                UserRole.USER_ROLE,
                null
        );
    }

}
