package com.pixelbloom.coreService.serviceImpl;

import com.pixelbloom.coreService.enums.MemberStatus;
import com.pixelbloom.coreService.exception.MemberNotFoundException;
import com.pixelbloom.coreService.model.memberModel.Member;
import com.pixelbloom.coreService.repository.CustomerRepository;
import com.pixelbloom.coreService.requestDto.CreateCustomerRequest;
import com.pixelbloom.coreService.requestDto.UpdateCustomerRequest;
import com.pixelbloom.coreService.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Member customer = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .status(MemberStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        customer = customerRepository.save(customer);
        return toResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Member customer = customerRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Customer not found with id: " + id));
        return toResponse(customer);
    }

    private CustomerResponse toResponse(Member customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .city(customer.getCity())
                .state(customer.getState())
                .pincode(customer.getPincode())
                .status(customer.getStatus())
                .build();
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(Long id, UpdateCustomerRequest request) {
        Member customer = customerRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Customer not found with id: " + id));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setPincode(request.getPincode());
        customer.setUpdatedAt(LocalDateTime.now());

        customer = customerRepository.save(customer);
        return toResponse(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new MemberNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

}