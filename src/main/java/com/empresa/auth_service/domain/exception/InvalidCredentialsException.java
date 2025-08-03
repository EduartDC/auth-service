package com.empresa.auth_service.domain.exception;

public class InvalidCredentialsException extends RuntimeException {
		public InvalidCredentialsException(String message) {
				super(message);
		}
}
