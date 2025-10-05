package com.store.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PasswordDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ForgetRequest {
        @NotBlank(message = "이메일은 필수입니다.")
        private String email;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResetRequest {
        @NotBlank(message = "재설정 토큰은 필수입니다.")
        private String token;

        @NotBlank(message = "새 비밀번호는 필수입니다.")
        @Size(min = 8, max = 150, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String newPassword;

        @NotBlank(message = "이메일은 필수입니다.")
        private String email;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChangeRequest {
        @NotBlank(message = "현재 비밀번호는 필수입니다.")
        private String currentPassword;

        @NotBlank(message = "새 비밀번호는 필수입니다.")
        @Size(min = 8, max = 150, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String newPassword;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String message; // 예: "비밀번호가 성공적으로 변경되었습니다." 같은 메시지
    }
}
