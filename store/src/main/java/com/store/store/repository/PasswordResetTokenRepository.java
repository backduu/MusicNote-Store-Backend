package com.store.store.repository;

import com.store.store.domain.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    // 토큰 값으로 조회
    Optional<PasswordResetToken> findByToken(String token);

    // 이메일로 조회
    Optional<PasswordResetToken> findByEmail(String email);

}
