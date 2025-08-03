package com.empresa.auth_service.domain.exception;

public class AccountLockedException extends RuntimeException {
		public AccountLockedException(String message) {
				super(message);
		}
}
