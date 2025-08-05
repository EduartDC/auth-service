package com.empresa.auth_service.application.service;

import org.springframework.stereotype.Service;
import com.empresa.auth_service.adapter.in.web.dto.request.LoginRequest;
import com.empresa.auth_service.adapter.in.web.dto.response.LoginResponse;
import com.empresa.auth_service.application.port.in.AuthUseCase;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthUseCase {

	@Override
	public LoginResponse login(LoginRequest request) {
		// Implement login logic
		return new LoginResponse();
	}

}
