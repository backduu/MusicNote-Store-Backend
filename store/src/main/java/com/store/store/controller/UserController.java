package com.store.store.controller;

import com.store.store.domain.entity.User;
import com.store.store.dto.UserDTO;
import com.store.store.repository.UserRepository;
import com.store.store.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "User API", description = "사용자 계정 관련 API (회원가입, 내 정보 관리 등)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Operation(summary = "모든 유저 검색", description = "상태가 'ACTIVE'인 전체 사용자를 조회합니다.")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO.UpdateRequest>> getAllUsers() {
        List<UserDTO.UpdateRequest> users = userService.getAllUsers();

        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다. 가입 시 상태는 'PENDING'이 됩니다.")
    @PostMapping("/signup")
    public ResponseEntity<UserDTO.Response> signupUser(@RequestBody UserDTO.Request userDTO) {
        return ResponseEntity.ok(userService.signup(userDTO));
    }

    @Operation(summary = "회원탈퇴", description = "회원탈퇴를 진행합니다. 탈퇴 시 상태는 'DELETED'가 됩니다.")
    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdraw(Principal principal) {
        userService.withdraw(principal.getName());
        return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
    }
}
