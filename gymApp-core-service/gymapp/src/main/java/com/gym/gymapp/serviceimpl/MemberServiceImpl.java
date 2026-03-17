package com.gym.gymapp.serviceimpl;

import com.gym.gymapp.dto.MemberDTO;
import com.gym.gymapp.entity.Member;
import com.gym.gymapp.mapper.MemberMapper;
import com.gym.gymapp.repository.MemberRepository;
import com.gym.gymapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = memberMapper.toEntity(memberDTO);
        return memberMapper.toDTO(memberRepository.save(member));
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        return memberMapper.toDTO(memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id)));
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        existing.setFirstName(memberDTO.getFirstName());
        existing.setLastName(memberDTO.getLastName());
        existing.setEmail(memberDTO.getEmail());
        existing.setJoinDate(memberDTO.getJoinDate());
        return memberMapper.toDTO(memberRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id)));
    }
}
