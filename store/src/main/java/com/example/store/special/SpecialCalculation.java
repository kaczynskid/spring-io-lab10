package com.example.store.special;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
public class SpecialCalculation {

	private final String specialId;

	private final BigDecimal totalPrice;
}
