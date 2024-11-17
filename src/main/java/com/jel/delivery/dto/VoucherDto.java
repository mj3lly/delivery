package com.jel.delivery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VoucherDto {

    private String code;
    private float discount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiry;

}
