package com.empresa.auth_service.adapter.in.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorDTO {
	private String message;
	private String error;
	private int status;
	private String timestamp;

}
