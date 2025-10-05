package com.store.store.service;

import com.store.store.dto.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {
    LoginDTO.Response login(LoginDTO.Request dto, HttpServletRequest request);
}
