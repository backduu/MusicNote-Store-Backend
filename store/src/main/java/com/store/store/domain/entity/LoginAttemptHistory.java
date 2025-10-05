package com.store.store.domain.entity;

import com.store.store.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author  백두현
 * @version 1.0
 * @since   2025-10-04
 * @description 비밀번호 불일치, 존재하지 않는 계정에 대한 접근에 대한 히스토리 엔티티
 */
@Table(name = "login_attempt_history")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Getter
@Builder
public class LoginAttemptHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 사용자가 시도했는지 (username 기준)
    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private boolean success;

    // 로그인 시도 IP
    @Column(length = 45)
    private String ipAddress;

    // 실패 사유 (비밀번호 불일치, 존재하지 않는 계정)
    @Column(length = 200)
    private String reason;

    // role
    @Column(length = 20)
    private UserStatus status;

    @CreatedDate
    private LocalDateTime attemptedAt;
}
