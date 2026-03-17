package com.gym.gymapp.serviceimpl;

import com.gym.gymapp.dto.AttendanceDTO;
import com.gym.gymapp.dto.MonthlyReportDTO;
import com.gym.gymapp.entity.Attendance;
import com.gym.gymapp.mapper.AttendanceMapper;
import com.gym.gymapp.repository.AttendanceRepository;
import com.gym.gymapp.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    @Transactional
    public AttendanceDTO checkIn(Long memberId) {
        Attendance attendance = new Attendance();
        attendance.setMemberId(memberId);
        attendance.setCheckInTime(LocalDateTime.now());
        return attendanceMapper.toDTO(attendanceRepository.save(attendance));
    }

    @Override
    @Transactional
    public AttendanceDTO checkOut(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found with id: " + attendanceId));
        if (attendance.getCheckOutTime() != null)
            throw new RuntimeException("Already checked out for this session");
        attendance.setCheckOutTime(LocalDateTime.now());
        return attendanceMapper.toDTO(attendanceRepository.save(attendance));
    }

    @Override
    public MonthlyReportDTO getMonthlyReport(Long memberId, int month, int year) {
        Long totalVisits = attendanceRepository.countMonthlyVisits(memberId, month, year);
        return new MonthlyReportDTO(memberId, month, year, totalVisits);
    }
}
