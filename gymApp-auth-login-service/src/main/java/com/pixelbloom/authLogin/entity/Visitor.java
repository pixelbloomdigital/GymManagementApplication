package com.pixelbloom.authLogin.entity;

import com.pixelbloom.authLogin.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitorId;

    private String name;
    private String email;
    private String phone;
    public String gymCenter;

    @CreationTimestamp
    public LocalDateTime visitedAt;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private String preferredBatchesJson;
    // store batches as JSON string for simplicity

    @Enumerated(EnumType.STRING)
    private Role role = Role.VISITOR;
}
