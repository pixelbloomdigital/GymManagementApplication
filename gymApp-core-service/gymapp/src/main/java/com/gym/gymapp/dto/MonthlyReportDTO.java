package com.gym.gymapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReportDTO {
    private Long memberId;
    private int month;
    private int year;
    private Long totalVisits;
}
