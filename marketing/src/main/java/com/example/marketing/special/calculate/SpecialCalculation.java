package com.example.marketing.special.calculate;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class SpecialCalculation {

	private final String specialId;

	private final BigDecimal totalPrice;
}
