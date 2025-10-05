package com.store.store.service.impl;

import com.store.store.component.JwtTokenProvider;
import com.store.store.domain.entity.PasswordResetToken;
import com.store.store.domain.entity.User;
import com.store.store.dto.LoginDTO;
import com.store.store.dto.PasswordDTO;
import com.store.store.repository.PasswordResetTokenRepository;
import com.store.store.repository.UserRepository;
import com.store.store.service.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service("passwordServiceImpl")
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PasswordServiceImpl implements PasswordService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    PasswordResetToken passwordResetToken;
    JwtTokenProvider  jwtTokenProvider;
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordDTO.Response change(String username, PasswordDTO.ChangeRequest dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 비밀번호 검증 및 새 비밀번호 validation
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (dto.getNewPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상이어야 합니다.");
        }

        String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
        user.changePassword(encodedPassword);
        userRepository.save(user);

        log.info("비밀번호 변경 성공: username={}", user.getUsername());

        return PasswordDTO.Response.builder()
                .message("비밀번호가 성공적으로 변경되었습니다.")
                .build();
    }

    @Override
    public PasswordDTO.Response reset(PasswordDTO.ResetRequest dto, HttpServletRequest request) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        PasswordResetToken tokenEntity = passwordResetTokenRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));

        // 토큰 검증
        if (tokenEntity.isUsed() || tokenEntity.isExpired() || !tokenEntity.getEmail().equals(user.getEmail())) {
            throw new IllegalStateException("이미 사용되었거나 만료된 토큰입니다.");
        }

        String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
        user.changePassword(encodedPassword);
        userRepository.save(user);

        // 토큰 사용 처리
        tokenEntity.markAsUsed();
        passwordResetTokenRepository.save(tokenEntity);

        log.info("비밀번호 재설정 성공: email={}, ip={}", user.getEmail(), request.getRemoteAddr());

        return PasswordDTO.Response.builder()
                .message("비밀번호가 성공적으로 재설정되었습니다.")
                .build();
    }

    @Override
    @Transactional
    public void forget(PasswordDTO.ForgetRequest dto, HttpServletRequest request) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾지 못했습니다."));

        // Reset Token 발급
        String resetToken = jwtTokenProvider.createResetToken(user.getEmail(), Map.of());

        // Reset Token db 저장
        PasswordResetToken entity = PasswordResetToken.builder()
                                            .email(user.getEmail())
                                            .token(resetToken)
                                            .expiryDate(LocalDateTime.now().plusMinutes(30))
                                            .used(false)
                                            .build();

        passwordResetTokenRepository.save(entity);

        // TODO Amazon SES 연동 시 이메일 발송 서비스 추가
        String resetLink = "https://music-note.com/reset?token=" + resetToken;
        // mailService.sendPasswordResetEmail(user.getEmail(), resetLink);

        log.info("비밀번호 재설정 링크 발급 {} : {}", user.getEmail(), resetToken);
    }
}
