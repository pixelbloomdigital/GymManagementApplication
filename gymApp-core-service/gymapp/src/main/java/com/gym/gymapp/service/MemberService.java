package com.gym.gymapp.service;

import com.gym.gymapp.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO createMember(MemberDTO memberDTO);
    MemberDTO getMemberById(Long id);
    List<MemberDTO> getAllMembers();
    MemberDTO updateMember(Long id, MemberDTO memberDTO);
    void deleteMember(Long id);
}
