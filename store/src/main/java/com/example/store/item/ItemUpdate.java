package com.example.store.item;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class ItemUpdate {

	private long id;

	private String name;

	private BigDecimal price;

	ItemUpdate withId(long id) {
		this.id = id;
		return this;
	}
}
