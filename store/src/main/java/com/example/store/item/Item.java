package com.example.store.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotNull
	@Lob
	private String name;

	@NotNull
	private int count = 0;

	@NotNull
	@DecimalMin("0.01")
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
