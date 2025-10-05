package com.store.store.controller;

import com.store.store.dto.LoginDTO;
import com.store.store.dto.PasswordDTO;
import com.store.store.service.LoginService;
import com.store.store.service.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;
    private final PasswordService passwordService;

    @Operation(summary = "로그인", description = "JWT 토큰과 함께 로그인할 사용자 정보를 DB에서 탐색하며 여러 데이터 검증을 거쳐 로그인 여부를 반환합니다.")
    @PostMapping("/login")
    public LoginDTO.Response login(@Valid @RequestBody LoginDTO.Request dto, HttpServletRequest request) {
        return loginService.login(dto, request);
    }

    @Operation(summary = "비밀번호 재설정 이메일 발송", description = "사용자가 비밀번호를 재설정할 수 있는 링크를 메일로 수신합니다.")
    @PostMapping("/forget")
    public void forget(@Valid @RequestBody PasswordDTO.ForgetRequest dto, HttpServletRequest request) {
        passwordService.forget(dto, request);
    }

    @Operation(summary = "비밀번호 재설정", description = "사용자가 비밀번호를 재설정하고 토큰 조회, 검증, 사용처리를 합니다.")
    @PostMapping("/reset")
    public ResponseEntity<PasswordDTO.Response> resetPassword(
            @Valid @RequestBody PasswordDTO.ResetRequest request,
            HttpServletRequest httpRequest) {
        return ResponseEntity.ok(passwordService.reset(request, httpRequest));
    }

    @Operation(summary = "로그인 사용자의 비밀번호 변경", description = "로그인한 사용자가 비밀번호를 변경합니다.")
    @PostMapping("/change")
    public ResponseEntity<PasswordDTO.Response> changePassword(
            @Valid @RequestBody PasswordDTO.ChangeRequest request,
            Principal principal) {
        return ResponseEntity.ok(passwordService.change(principal.getName(), request));
    }
}
