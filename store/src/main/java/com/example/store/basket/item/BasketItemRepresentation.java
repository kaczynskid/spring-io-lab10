package com.example.store.basket.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketItemRepresentation {

	@JsonIgnore
	private Long itemId;

	private String name;

	private int count;

	private BigDecimal price;

	public static BasketItemRepresentation of(BasketItem item) {
		return new BasketItemRepresentation(item.getItemId(), item.getName(), item.getUnitCount(), item.getTotalPrice());
	}
}
