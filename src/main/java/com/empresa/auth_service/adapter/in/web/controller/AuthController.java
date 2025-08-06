package com.empresa.auth_service.adapter.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.auth_service.adapter.in.web.dto.request.LoginRequest;
import com.empresa.auth_service.adapter.in.web.dto.response.LoginResponse;
import com.empresa.auth_service.application.port.in.AuthUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthUseCase authUseCase;

	@PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		LoginResponse response = authUseCase.login(request);
		return ResponseEntity.ok(response);
    }
}
