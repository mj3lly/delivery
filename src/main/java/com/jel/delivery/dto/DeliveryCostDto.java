package com.jel.delivery.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DeliveryCostDto {

    private String costType;
    private BigDecimal deliveryCost;

}
