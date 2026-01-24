package com.pixelbloom.authLogin.service;

import com.pixelbloom.authLogin.requestdto.*;
import com.pixelbloom.authLogin.responsedto.*;

public interface AuthService {
    VisitorRegisterResponse registerVisitor(VisitorRegisterRequest request);
    void convertVisitorToMember(AdminCreateMemberRequest request);
    LoginResponse login(LoginRequest request);
    void registerAdmin(AdminRegisterRequest request);
}