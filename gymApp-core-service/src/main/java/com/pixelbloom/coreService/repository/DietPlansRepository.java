package com.pixelbloom.coreService.repository;

import com.pixelbloom.coreService.entity.DietPlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DietPlansRepository extends JpaRepository <DietPlansEntity,Long> {

}
