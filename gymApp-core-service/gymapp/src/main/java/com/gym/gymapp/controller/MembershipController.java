package com.gym.gymapp.controller;

import com.gym.gymapp.dto.MembershipDTO;
import com.gym.gymapp.service.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
@Tag(name = "Membership Management", description = "APIs for managing gym memberships")
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping
    @Operation(summary = "Create a new membership")
    public ResponseEntity<MembershipDTO> createMembership(@Valid @RequestBody MembershipDTO membershipDTO) {
        return new ResponseEntity<>(membershipService.createMembership(membershipDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get membership by ID")
    public ResponseEntity<MembershipDTO> getMembershipById(@PathVariable Long id) {
        return ResponseEntity.ok(membershipService.getMembershipById(id));
    }

    @GetMapping
    @Operation(summary = "Get all memberships")
    public ResponseEntity<List<MembershipDTO>> getAllMemberships() {
        return ResponseEntity.ok(membershipService.getAllMemberships());
    }

    @GetMapping("/member/{memberId}")
    @Operation(summary = "Get memberships by member ID")
    public ResponseEntity<List<MembershipDTO>> getMembershipsByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(membershipService.getMembershipsByMemberId(memberId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update membership")
    public ResponseEntity<MembershipDTO> updateMembership(@PathVariable Long id, @Valid @RequestBody MembershipDTO membershipDTO) {
        return ResponseEntity.ok(membershipService.updateMembership(id, membershipDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete membership")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.noContent().build();
    }
}
