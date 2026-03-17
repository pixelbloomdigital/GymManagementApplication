package com.gym.gymapp.controller;

import com.gym.gymapp.dto.AttendanceDTO;
import com.gym.gymapp.dto.CheckInRequest;
import com.gym.gymapp.dto.MonthlyReportDTO;
import com.gym.gymapp.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@Tag(name = "Attendance Management", description = "APIs for managing gym attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check-in")
    @Operation(summary = "Record member check-in")
    public ResponseEntity<AttendanceDTO> checkIn(@Valid @RequestBody CheckInRequest request) {
        return new ResponseEntity<>(attendanceService.checkIn(request.getMemberId()), HttpStatus.CREATED);
    }

    @PutMapping("/check-out/{attendanceId}")
    @Operation(summary = "Record member check-out")
    public ResponseEntity<AttendanceDTO> checkOut(@PathVariable Long attendanceId) {
        return ResponseEntity.ok(attendanceService.checkOut(attendanceId));
    }

    @GetMapping("/report/{memberId}/{month}/{year}")
    @Operation(summary = "Get monthly attendance report for a member")
    public ResponseEntity<MonthlyReportDTO> getMonthlyReport(
            @PathVariable Long memberId,
            @PathVariable int month,
            @PathVariable int year) {
        return ResponseEntity.ok(attendanceService.getMonthlyReport(memberId, month, year));
    }
}
