package com.gym.gymapp.mapper;

import com.gym.gymapp.dto.MemberDTO;
import com.gym.gymapp.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberDTO toDTO(Member member);
    Member toEntity(MemberDTO memberDTO);
}
