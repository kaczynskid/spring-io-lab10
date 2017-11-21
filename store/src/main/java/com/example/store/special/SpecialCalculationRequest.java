package com.example.store.special;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialCalculationRequest {

    private BigDecimal unitPrice;
    private int unitCount;

    public static SpecialCalculationRequest requestCalculationFor(BigDecimal unitPrice, int unitCount) {
        return new SpecialCalculationRequest(unitPrice, unitCount);
    }
}
