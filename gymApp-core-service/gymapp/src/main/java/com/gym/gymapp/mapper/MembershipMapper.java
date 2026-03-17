package com.gym.gymapp.mapper;

import com.gym.gymapp.dto.MembershipDTO;
import com.gym.gymapp.entity.Membership;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembershipMapper {
    MembershipDTO toDTO(Membership membership);
    Membership toEntity(MembershipDTO membershipDTO);
}
