package com.store.store.controller;

import com.store.store.dto.LoginDTO;
import com.store.store.service.LoginService;
import com.store.store.service.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;
   // private final PasswordService passwordService;

    @PostMapping("/login")
    public LoginDTO.Response login(@Valid @RequestBody LoginDTO.Request dto, HttpServletRequest request) {
        return loginService.login(dto, request);
    }
}
