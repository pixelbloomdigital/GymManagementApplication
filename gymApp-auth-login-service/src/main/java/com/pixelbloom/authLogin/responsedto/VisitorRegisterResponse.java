package com.pixelbloom.authLogin.responsedto;

import com.pixelbloom.authLogin.entity.BatchDto;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class VisitorRegisterResponse {
    private Long visitorId;
    private String status;
    private String name;
    private String email;
    private String phone;
    private String gymCenter;
 //   private String vistedAt;
    private List<BatchDto> preferredBatches;
}