package com.pixelbloom.authLogin.controller;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.pixelbloom.authLogin.requestdto.*;
import com.pixelbloom.authLogin.responsedto.*;
import com.pixelbloom.authLogin.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService service;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }


    //@GetMapping("/admin/findVistorByDate/{date}")
    //public List<Visitor> findVistorByDate(@PathVariable Date date){
    //}


    //@GetMapping("/admin/findVistorByName/{name}")
    //public List<Visitor> findVistorByName(@PathVariable String name){
    //}



}