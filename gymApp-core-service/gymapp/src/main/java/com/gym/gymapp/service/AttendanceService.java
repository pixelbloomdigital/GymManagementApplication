package com.gym.gymapp.service;

import com.gym.gymapp.dto.AttendanceDTO;
import com.gym.gymapp.dto.MonthlyReportDTO;

public interface AttendanceService {
    AttendanceDTO checkIn(Long memberId);
    AttendanceDTO checkOut(Long attendanceId);
    MonthlyReportDTO getMonthlyReport(Long memberId, int month, int year);
}
