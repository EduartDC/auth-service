package com.empresa.auth_service.adapter.in.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.auth_service.adapter.in.web.dto.request.LoginRequest;
import com.empresa.auth_service.adapter.in.web.dto.response.LoginResponse;
import com.empresa.auth_service.application.port.in.AuthUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	private static final Logger authAuditLogger = LoggerFactory.getLogger("AUTH_AUDIT");

	private final AuthUseCase authUseCase;

	@PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest) {
		String clientIp = getClientIp(httpRequest);
		logger.info("Intento de login para usuario: {} desde IP: {}", request.getUsername(), clientIp);
		LoginResponse response = authUseCase.login(request);
		authAuditLogger.info("Login exitoso - Usuario: {} - IP: {} - Token generado",
			request.getUsername(), clientIp);
		return ResponseEntity.ok(response);
    }

	/**
	 * Extrae la IP real del cliente considerando proxies y load balancers
	 */
	private String getClientIp(HttpServletRequest request) {
		String xForwardedFor = request.getHeader("X-Forwarded-For");
		if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
			return xForwardedFor.split(",")[0].trim();
		}
		String xRealIp = request.getHeader("X-Real-IP");
		if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
			return xRealIp;
		}
		return request.getRemoteAddr();
	}
}
