package com.jel.delivery.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Min;

@Data
@Builder
public class ParcelRequestDto {

    @Min(value = 1, message = "Weight must be at least 1")
    private float weight;

    @Min(value = 1, message = "Height must be at least 1")
    private float height;

    @Min(value = 1, message = "Width must be at least 1")
    private float width;

    @Min(value = 1, message = "Length must be at least 1")
    private float length;

    private String voucherCode;

}
