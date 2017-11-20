package com.example.store.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	private Long id;

	private String name;

	private int count = 0;

	private BigDecimal price = ZERO;

	void update(ItemUpdate changes) {
		Assert.notNull(changes, "Changes cannot be null");

		name = changes.getName();
		price = changes.getPrice();
	}

	void updateStock(ItemStockUpdate changes) {
		Assert.notNull(changes, "Changes cannot be null");
		Assert.isTrue(changes.getCountDiff() != 0, "CountDiff cannot be zero");

		this.count = changes.applyFor(this);
	}
}
