package com.jel.delivery.service;

import com.jel.delivery.dto.DeliveryCostDto;
import com.jel.delivery.dto.ParcelRequestDto;
import com.jel.delivery.dto.VoucherDto;
import com.jel.delivery.rule.CalculationParam;
import com.jel.delivery.rule.DeliveryCostCalculationRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.jel.delivery.rule.DeliveryCostCalculationRule.REJECT;

@Slf4j
@Service
public class DeliveryCostService {

    private static final Function<ParcelRequestDto, Float> CALCULATE_VOLUME =
            parcel -> parcel.getHeight() * parcel.getWidth() * parcel.getLength();

    private final VoucherService voucherService;

    public DeliveryCostService(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    public DeliveryCostDto calculateDeliveryCost(ParcelRequestDto requestDto) {
        CalculationParam param = CalculationParam.builder()
                .weight(requestDto.getWeight())
                .volume(CALCULATE_VOLUME.apply(requestDto))
                .build();

        VoucherDto voucher = this.voucherService.retrieveVoucher(requestDto.getVoucherCode());
        Pair<String, BigDecimal> pair = DeliveryCostCalculationRule.calculateDeliveryCost(param, voucher);

        if (REJECT.name().equalsIgnoreCase(pair.getKey())) {

        }

        return DeliveryCostDto
                .builder()
                    .costType(pair.getKey())
                    .deliveryCost(pair.getValue())
                .build();
    }

}
