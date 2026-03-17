package com.gym.gymapp.service;

import com.gym.gymapp.dto.MembershipDTO;

import java.util.List;

public interface MembershipService {
    MembershipDTO createMembership(MembershipDTO membershipDTO);
    MembershipDTO getMembershipById(Long id);
    List<MembershipDTO> getAllMemberships();
    List<MembershipDTO> getMembershipsByMemberId(Long memberId);
    MembershipDTO updateMembership(Long id, MembershipDTO membershipDTO);
    void deleteMembership(Long id);
}
