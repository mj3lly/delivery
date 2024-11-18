package com.jel.delivery.rule;

import com.jel.delivery.dto.VoucherDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BiFunction;

@UtilityClass
public final class DeliveryCostConstant {

    public static final String UNKNOWN_RULE = "UNKNOWN_RULE";
    public static final BiFunction<Float, VoucherDto, BigDecimal> HEAVY_PARCEL = (weight, voucher)
            -> BigDecimal.valueOf(computeTotalDiscountedCost(20.0 * weight, voucher));
    public static final BiFunction<Float, VoucherDto, BigDecimal> SMALL_PARCEL = (volume, voucher)
            -> BigDecimal.valueOf(computeTotalDiscountedCost(0.03 * volume, voucher));
    public static final BiFunction<Float, VoucherDto, BigDecimal> MEDIUM_PARCEL = (volume, voucher)
            -> BigDecimal.valueOf(computeTotalDiscountedCost(0.04 * volume, voucher));
    public static final BiFunction<Float, VoucherDto, BigDecimal>LARGE_PARCEL = (volume, voucher)
            -> BigDecimal.valueOf(computeTotalDiscountedCost(0.05 * volume, voucher));

    private static float computeTotalDiscountedCost(Double initialCost, VoucherDto voucher) {
        LocalDate localDate = LocalDate.now();
        if (voucher.getExpiry().isBefore(localDate)) {
            voucher.setDiscount(0);
        }

        return (float) (initialCost - (initialCost * (voucher.getDiscount() / 100)));
    }

}
