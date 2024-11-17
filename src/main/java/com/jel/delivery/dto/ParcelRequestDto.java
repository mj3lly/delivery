package com.jel.delivery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParcelRequestDto {

    private float weight;
    private float height;
    private float width;
    private float length;
    private String voucherCode;

}
