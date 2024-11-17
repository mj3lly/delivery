package com.jel.delivery.rule;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculationParam {

    private float weight;
    private float volume;

}
