package com.example.store.basket.item;

import lombok.Value;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Value
public class BasketUpdateDiff {

	private final int countDiff;

	private final BigDecimal priceDiff;

	public BasketUpdateDiff(int countDiff, BigDecimal priceDiff) {
		Assert.notNull(priceDiff, "PriceDiff cannot be null");
		
		this.countDiff = countDiff;
		this.priceDiff = priceDiff;
	}

	public static BasketUpdateDiff ofItem(BasketItem item) {
		return new BasketUpdateDiff(-item.getUnitCount(), item.getTotalPrice().negate());
	}
}
