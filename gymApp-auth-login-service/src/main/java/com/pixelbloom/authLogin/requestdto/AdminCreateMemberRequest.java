package com.pixelbloom.authLogin.requestdto;

import lombok.*;

@Getter @Setter
public class AdminCreateMemberRequest {
    public Long visitorId;
    public String password;
    public String gymCenter;
}