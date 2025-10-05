package com.store.store.repository;

import com.store.store.domain.entity.LoginAttemptHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginAttemptHistoryRepository extends JpaRepository<LoginAttemptHistory, Long> {
    // 사용자의 로그인 히스토리 조회
    List<LoginAttemptHistory> findByUsername(String username);

    // 사용자 별 로그인 시도 횟수
    List<LoginAttemptHistory> findTop5ByUsernameOrderByAttemptedAtDesc(String username);

}

