package com.jel.delivery.rule;

import com.jel.delivery.dto.VoucherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.jel.delivery.rule.DeliveryCostConstant.*;

@Getter
@AllArgsConstructor
public enum DeliveryCostCalculationRule {

    REJECT(param -> param.getWeight() > 50.0,
            (param, voucher) -> BigDecimal.ZERO),
    HEAVY(param -> param.getWeight() > 10.0,
            (param, voucher) -> HEAVY_PARCEL.apply(param.getWeight(), voucher)),
    SMALL(param -> param.getVolume() < 1500.0,
            (param, voucher) -> SMALL_PARCEL.apply(param.getVolume(), voucher)),
    MEDIUM(param -> param.getVolume() < 2500.0,
            (param, voucher) -> MEDIUM_PARCEL.apply(param.getVolume(), voucher)),
    LARGE(param -> true,
            (param, voucher) -> LARGE_PARCEL.apply(param.getVolume(), voucher));

    private final Predicate<CalculationParam> calculationRule;
    private final BiFunction<CalculationParam, VoucherDto, BigDecimal> calculateCost;

    public static Pair<String, BigDecimal> calculateDeliveryCost(CalculationParam param, VoucherDto voucher) {
        return Arrays.stream(DeliveryCostCalculationRule.values())
                .filter(rule -> rule.getCalculationRule().test(param))
                .map(rule -> Pair.of(rule.name(), rule.getCalculateCost().apply(param, voucher)))
                .findFirst()
                .orElse(Pair.of(UNKNOWN_RULE, BigDecimal.ZERO));
    }

}
