package com.empresa.auth_service.application.service;

import org.springframework.stereotype.Service;
import com.empresa.auth_service.adapter.in.web.dto.request.LoginRequest;
import com.empresa.auth_service.adapter.in.web.dto.response.LoginResponse;
import com.empresa.auth_service.application.port.in.AuthUseCase;
import com.empresa.auth_service.domain.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthUseCase {

	@Override
	public LoginResponse login(LoginRequest request) {
		// Validación básica temporal (reemplaza con tu lógica real)
		if ("admin".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
			LoginResponse response = new LoginResponse();
			response.setToken("fake-jwt-token-12345");
			response.setRole("ADMIN");
			response.setUsername(request.getUsername());
			return response;
		} else {
			throw new InvalidCredentialsException("Usuario o contraseña incorrectos");
		}
	}
}
