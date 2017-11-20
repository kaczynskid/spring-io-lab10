package com.example.store.item;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ItemStockUpdate {

	private long id;

	private int countDiff;

	int applyFor(Item item) {
		int count = item.getCount();

		if (count + countDiff < 0) {
			throw new OutOfStock(item, countDiff);
		}

		return count + countDiff;
	}
}
