package com.empresa.auth_service.domain.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private int loginAttempts;
    private boolean accountLocked;
}
