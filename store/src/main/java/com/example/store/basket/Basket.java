package com.example.store.basket;

import com.example.store.MathProperties;
import com.example.store.basket.item.BasketUpdateDiff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static com.example.store.basket.BasketStatus.CLOSED;
import static com.example.store.basket.BasketStatus.OPEN;
import static java.math.BigDecimal.ZERO;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Basket {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(STRING)
	private BasketStatus status = OPEN;

	@NotNull
	@Min(0)
	private int totalCount = 0;

	@NotNull
	@Min(0)
	private BigDecimal totalPrice = ZERO;

	public Basket(Long id) {
		this.id = id;
	}

	void update(BasketUpdateDiff diff, MathProperties math) {
		Assert.notNull(diff, "BasketUpdateDiff cannot be null");
		Assert.notNull(math, "Math cannot be null");
		assertNotCheckedOut();

		int newCount = totalCount + diff.getCountDiff();
		Assert.isTrue(newCount >= 0, "Basket total count cannot be negative");

		BigDecimal newPrice = totalPrice.add(diff.getPriceDiff(), math.getContext());
		Assert.isTrue(newPrice.compareTo(ZERO) >= 0, "Basket total price cannot be negative");

		totalCount = newCount;
		totalPrice = newPrice;
	}

	void checkout() {
		assertNotCheckedOut();

		if (totalCount == 0) {
			throw new EmptyBasket(this);
		}

		status = CLOSED;
	}

	void assertNotCheckedOut() {
		if (CLOSED.equals(status)) {
			throw new AlreadyCheckedOut(this);
		}
	}
}
