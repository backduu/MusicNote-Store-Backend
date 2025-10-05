package com.store.store.service;

import com.store.store.dto.LoginDTO;
import com.store.store.dto.PasswordDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface PasswordService {
    void forget(PasswordDTO.ForgetRequest dto, HttpServletRequest request);
    PasswordDTO.Response reset(PasswordDTO.ResetRequest dto, HttpServletRequest request);
    PasswordDTO.Response change(String username, PasswordDTO.ChangeRequest dto);
}
