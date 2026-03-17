package com.gym.gymapp.serviceimpl;

import com.gym.gymapp.dto.MembershipDTO;
import com.gym.gymapp.entity.Membership;
import com.gym.gymapp.mapper.MembershipMapper;
import com.gym.gymapp.repository.MembershipRepository;
import com.gym.gymapp.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;

    @Override
    @Transactional
    public MembershipDTO createMembership(MembershipDTO membershipDTO) {
        return membershipMapper.toDTO(membershipRepository.save(membershipMapper.toEntity(membershipDTO)));
    }

    @Override
    public MembershipDTO getMembershipById(Long id) {
        return membershipMapper.toDTO(membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id)));
    }

    @Override
    public List<MembershipDTO> getAllMemberships() {
        return membershipRepository.findAll().stream()
                .map(membershipMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MembershipDTO> getMembershipsByMemberId(Long memberId) {
        return membershipRepository.findByMemberId(memberId).stream()
                .map(membershipMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MembershipDTO updateMembership(Long id, MembershipDTO membershipDTO) {
        Membership existing = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));
        existing.setType(membershipDTO.getType());
        existing.setPrice(membershipDTO.getPrice());
        existing.setDuration(membershipDTO.getDuration());
        existing.setMemberId(membershipDTO.getMemberId());
        return membershipMapper.toDTO(membershipRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteMembership(Long id) {
        membershipRepository.delete(membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id)));
    }
}
