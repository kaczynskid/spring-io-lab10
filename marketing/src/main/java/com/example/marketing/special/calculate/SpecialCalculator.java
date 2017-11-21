package com.example.marketing.special.calculate;

import com.example.marketing.MathProperties;

import java.math.BigDecimal;

@FunctionalInterface
public interface SpecialCalculator {

	SpecialCalculator regularPrice = (unitCount, unitPrice, math) ->
			new SpecialCalculation(null, unitPrice.multiply(BigDecimal.valueOf(unitCount)));

	SpecialCalculation calculateFor(int unitCount, BigDecimal unitPrice, MathProperties math);
}
