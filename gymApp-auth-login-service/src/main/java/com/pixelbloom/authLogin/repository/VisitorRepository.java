package com.pixelbloom.authLogin.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pixelbloom.authLogin.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}