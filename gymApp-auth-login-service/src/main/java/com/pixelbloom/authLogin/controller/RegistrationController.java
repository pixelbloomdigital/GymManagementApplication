package com.pixelbloom.authLogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.pixelbloom.authLogin.requestdto.*;
import com.pixelbloom.authLogin.responsedto.*;
import com.pixelbloom.authLogin.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final AuthService service;

    // Visitor Registration
    @PostMapping("/visitor/register")
    public VisitorRegisterResponse registerVisitor(
            @RequestBody VisitorRegisterRequest request) {
        return service.registerVisitor(request);
        // also send this visitor data to visitor controller in core service keep here
        // only login related functionality
    }

    // Admin creates Member from Visitor
    @PostMapping("/admin/create-member")
    public String convertToMember(@RequestBody AdminCreateMemberRequest request) {
        service.convertVisitorToMember(request);
        return "Member Created with role USER";

        //send this member data to member controller in core service
        //keep here only login related functionality at min
    }

    // Admin self registration
    @PostMapping("/admin/register")
    public String registerAdmin(@RequestBody AdminRegisterRequest request) {
        service.registerAdmin(request);
        return "Admin Registered";
    }
}
