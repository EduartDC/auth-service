package com.empresa.auth_service.adapter.in.web.dto.request;

import lombok.*;
@Getter @Setter
public class LoginRequest {
	private String username;
	private String password;
}
