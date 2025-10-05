package com.store.store.controller;

import com.store.store.domain.enums.UserRole;
import com.store.store.domain.enums.UserStatus;
import com.store.store.dto.UserDTO;
import com.store.store.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin API", description = "관리자 페이지 관련 (사용자 목록 조회'페이징처리',)")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @Operation(summary = "모든 유저 검색", description = "모든 유저 조회 페이징 처리합니다. ")
    @GetMapping("/users")
    public ResponseEntity<Page<UserDTO.Response>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "created") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(userService.findAllUsers(pageable));
    }

    @Operation(summary = "username으로 특정 유저 검색", description = "특정 유저를 조회합니다.")
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO.Response> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.findUser(username));
    }

    @Operation(summary = "상태 별 유저 검색", description = "유저의 상태 별로 모든 유저 검색합니다.")
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<UserDTO.Response>> getAllUsersByStatus(
            @PathVariable UserStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "created") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(userService.findUsersByStatus(status, pageable));
    }

    @Operation(summary = "상태 및 역할 별 검색", description = "유저의 상태 및 역할 별로 모든 유저 검색합니다.")
    @GetMapping("/status/role")
    public ResponseEntity<Page<UserDTO.Response>> getUsersByStatusAndRole(
            @RequestParam UserStatus status,
            @RequestParam UserRole role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "created") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(userService.findUsersByStatusAndRole(status, role, pageable));
    }
}
