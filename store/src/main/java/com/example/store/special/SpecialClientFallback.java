package com.example.store.special;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpecialClientFallback implements SpecialClient {

    @Override
    public SpecialCalculation calculateFor(long itemId, SpecialCalculationRequest request) {
        return new SpecialCalculation(null, request.getUnitPrice()
                .multiply(BigDecimal.valueOf(request.getUnitCount())));
    }
}
