package com.example.store.special;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialCalculationRequest {

    private int unitCount;

    public static SpecialCalculationRequest requestCalculationFor(int unitCount) {
        return new SpecialCalculationRequest(unitCount);
    }
}
