package com.pixelbloom.authLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pixelbloom.authLogin.entity.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}