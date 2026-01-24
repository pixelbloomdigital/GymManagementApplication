package com.pixelbloom.authLogin.serviceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixelbloom.authLogin.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pixelbloom.authLogin.entity.*;
import com.pixelbloom.authLogin.repository.*;
import com.pixelbloom.authLogin.requestdto.*;
import com.pixelbloom.authLogin.responsedto.*;
import com.pixelbloom.authLogin.jwt.JwtService;
import com.pixelbloom.authLogin.service.AuthService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final VisitorRepository visitorRepo;
    private final MemberRepository memberRepo;
    private final JwtService jwtService;
    private final ObjectMapper mapper = new ObjectMapper();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public VisitorRegisterResponse registerVisitor(VisitorRegisterRequest request) {
        try {
            Visitor v = new Visitor();
            v.setName(request.name);
            v.setEmail(request.email);
            v.setPhone(request.phone);
            v.setPreferredBatchesJson(mapper.writeValueAsString(request.preferredBatches));
            v.setGymCenter(request.gymCenter);
            v.setRole(Role.VISITOR);
            visitorRepo.save(v);

            return new VisitorRegisterResponse(
                    v.getVisitorId(),
                    "VISITOR",
                    v.getName(),
                    v.getEmail(),
                    v.getPhone(),
                    v.getGymCenter(),
                    request.preferredBatches );
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void convertVisitorToMember(AdminCreateMemberRequest request) {
        Visitor v = visitorRepo.findById(request.visitorId)
                .orElseThrow();

        Member m = new Member();
        m.setName(v.getName());
        m.setEmail(v.getEmail());
        m.setPhone(v.getPhone());
        m.setPassword(encoder.encode(request.password));
        m.setGymCenter(request.gymCenter);
        m.setRole(Role.USER);

        memberRepo.save(m);
        visitorRepo.delete(v);
    }

    @Override
    public void registerAdmin(AdminRegisterRequest request) {
        Member m = new Member();
        m.setName(request.getName());
        m.setPhone(request.getPhone());
        m.setEmail(request.getEmail());
        m.setPassword(encoder.encode(request.getPassword()));
        m.setRole(Role.ADMIN);
        m.setGymCenter(request.gymCenter);
        memberRepo.save(m);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Member m = memberRepo.findByEmail(request.getEmail())
                .orElseThrow();

        if(!encoder.matches(request.getPassword(), m.getPassword()))
            throw new RuntimeException("Invalid Password");

        String token = jwtService.generateToken(m.getEmail(), m.getRole().name());
        return new LoginResponse(token, m.getRole().name());
    }
}