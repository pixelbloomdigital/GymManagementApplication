package com.pixelbloom.coreService.controller;

import com.pixelbloom.coreService.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService customerService;

   //admin apis
    //1. @GetMapping("/admin/getAllmembers")
    //2. Get Member Details 	/members/{memberId}
    //3. PUT /api/members/{memberId}
    //4. DELETE /api/members/{memberId}



}
