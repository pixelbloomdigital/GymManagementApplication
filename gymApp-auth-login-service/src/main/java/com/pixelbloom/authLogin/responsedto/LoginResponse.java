package com.pixelbloom.authLogin.responsedto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String role;
}