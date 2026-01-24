package com.pixelbloom.authLogin.requestdto;
import com.pixelbloom.authLogin.entity.BatchDto;
import lombok.*;
import java.util.List;

@Data
public class VisitorRegisterRequest {
    public String name;
    public String email;
    public String phone;
    public String gymCenter;
    public List<BatchDto> preferredBatches;
}
