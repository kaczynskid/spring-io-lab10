package com.example.store.basket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketRepresentation {

	@JsonIgnore
	private Long id;

	private long totalCount;

	private BigDecimal totalPrice;

	public static BasketRepresentation of(Basket basket) {
		return new BasketRepresentation(basket.getId(), basket.getTotalCount(), basket.getTotalPrice());
	}
}
