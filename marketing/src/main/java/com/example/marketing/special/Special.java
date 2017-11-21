package com.example.marketing.special;

import com.example.marketing.MathProperties;
import com.example.marketing.special.calculate.SpecialCalculation;
import com.example.marketing.special.calculate.SpecialCalculator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Special implements SpecialCalculator {

	@Id
	private String id;

	private long itemId;

	private int batchSize = 1;

	private BigDecimal batchPrice = ZERO;

	public SpecialCalculation calculateFor(int unitCount, BigDecimal unitPrice, MathProperties math) {
		Assert.isTrue(unitCount > 0, "UnitCount must be positive");
		Assert.notNull(unitPrice, "UnitPrice cannot be null");
		Assert.isTrue(unitPrice.compareTo(ZERO) > 0, "UnitPrice must be positive");
		Assert.notNull(math, "Math cannot be null");

		if (specialPriceAppliesFor(unitCount)) {
			return calculateSpecialPrice(unitCount, unitPrice, math);
		} else {
			return regularPrice.calculateFor(unitCount, unitPrice, math);
		}
	}

	private boolean specialPriceAppliesFor(int unitCount) {
		return unitCount >= batchSize;
	}

	private SpecialCalculation calculateSpecialPrice(int unitCount, BigDecimal unitPrice, MathProperties math) {
		BigDecimal batchesPrice = batchesPriceOf(unitCount);
		BigDecimal reminderPrice = reminderPriceOf(unitCount, unitPrice);
		BigDecimal totalPrice = batchesPrice.add(reminderPrice, math.getContext());
		return new SpecialCalculation(id, totalPrice);
	}

	private BigDecimal batchesPriceOf(int unitCount) {
		int batchesCount = unitCount / batchSize;
		return batchPrice.multiply(BigDecimal.valueOf(batchesCount));
	}

	private BigDecimal reminderPriceOf(int unitCount, BigDecimal unitPrice) {
		int reminderCount = unitCount % batchSize;
		return unitPrice.multiply(BigDecimal.valueOf(reminderCount));
	}
}
