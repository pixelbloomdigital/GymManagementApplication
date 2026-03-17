package com.gym.gymapp.mapper;

import com.gym.gymapp.dto.AttendanceDTO;
import com.gym.gymapp.entity.Attendance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    AttendanceDTO toDTO(Attendance attendance);
    Attendance toEntity(AttendanceDTO attendanceDTO);
}
