package com.jel.delivery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoucherDto {

    private String code;
    private float discount;
    private String expiry;

}
