package com.example.store.basket.item;

import com.example.store.MathProperties;
import com.example.store.item.ItemRepresentation;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class BasketItem {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotNull
	private Long basketId;

	@NotNull
	private Long itemId;

	@NotNull
	@Column(columnDefinition = "text")
	private String name;

	@NotNull
	@DecimalMin("0.01")
	private BigDecimal unitPrice = ZERO;

	@NotNull
	@Min(1)
	private int unitCount = 0;

	@NotNull
	@DecimalMin("0.01")
	private BigDecimal totalPrice = ZERO;

	private String specialId;

	public BasketItem(Long basketId, Long itemId) {
		this.basketId = basketId;
		this.itemId = itemId;
	}

	BasketUpdateDiff update(ItemRepresentation changes, int newUnitCount, MathProperties math) {
		Assert.notNull(changes, "Item changes cannot be null");
		Assert.isTrue(newUnitCount > 0, "UnitCount must be positive");
		Assert.notNull(math, "Math cannot be null");

		name = changes.getName();
		unitPrice = changes.getPrice();
		return new BasketUpdateDiff(
				updateItemCount(newUnitCount),
				updateTotalPrice(changes.getPrice().multiply(BigDecimal.valueOf(newUnitCount)), math));
	}


	private int updateItemCount(int newUnitCount) {
		int diff = newUnitCount - unitCount;
		unitCount = newUnitCount;
		return diff;
	}

	private BigDecimal updateTotalPrice(BigDecimal newTotalPrice, MathProperties math) {
		BigDecimal diff = newTotalPrice.subtract(totalPrice, math.getContext());
		totalPrice = newTotalPrice;
		return diff;
	}
}
