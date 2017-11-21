package com.example.marketing.special;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialRepresentation {

	private long itemId;

	private int batchSize;

	private BigDecimal batchPrice;

	public static SpecialRepresentation of(Special special) {
		return new SpecialRepresentation(special.getItemId(), special.getBatchSize(), special.getBatchPrice());
	}

	public Special asSpecial() {
		return new Special(null, itemId, batchSize, batchPrice);
	}
}