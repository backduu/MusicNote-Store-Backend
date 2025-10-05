package com.store.store.service;

import com.store.store.dto.UserDTO;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<UserDTO.UpdateRequest> getAllUsers();

    UserDTO.Response signup(UserDTO.Request request);

    void withdraw(String username);
}
