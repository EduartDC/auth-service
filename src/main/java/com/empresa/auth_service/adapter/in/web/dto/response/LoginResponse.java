package com.empresa.auth_service.adapter.in.web.dto.response;


import lombok.*;
@Getter @Setter
public class LoginResponse {
	private String token;
	private String role;
	private String username;
}
