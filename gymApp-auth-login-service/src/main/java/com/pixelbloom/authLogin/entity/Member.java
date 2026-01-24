package com.pixelbloom.authLogin.entity;
import com.pixelbloom.authLogin.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;
    private String email;
    private String phone;

    private String password;
    public String gymCenter;
    @CreationTimestamp
    public LocalDateTime joinedAt;

    @Enumerated(EnumType.STRING)
    private Role role; // USER or ADMIN
}