package com.empresa.auth_service.application.port.in;

import com.empresa.auth_service.adapter.in.web.dto.request.LoginRequest;
import com.empresa.auth_service.adapter.in.web.dto.response.LoginResponse;

public interface AuthUseCase {
	LoginResponse login(LoginRequest request);
}
