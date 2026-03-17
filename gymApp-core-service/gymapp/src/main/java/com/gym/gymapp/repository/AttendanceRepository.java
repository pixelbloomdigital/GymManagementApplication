package com.gym.gymapp.repository;

import com.gym.gymapp.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query(value = "SELECT COUNT(*) FROM attendance WHERE memberId = :memberId " +
                   "AND YEAR(checkInTime) = :year AND MONTH(checkInTime) = :month " +
                   "AND checkOutTime IS NOT NULL",
           nativeQuery = true)
    Long countMonthlyVisits(@Param("memberId") Long memberId,
                            @Param("month") int month,
                            @Param("year") int year);
}
