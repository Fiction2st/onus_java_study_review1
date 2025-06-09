package com.onus.crud_review1.services;

import com.onus.crud_review1.dtos.user.UserDTO;
import com.onus.crud_review1.dtos.user.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDTO);
}
