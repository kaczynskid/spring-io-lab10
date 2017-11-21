package com.example.store.basket;

import com.example.store.MathProperties;
import com.example.store.basket.item.BasketItemService;
import com.example.store.basket.item.BasketUpdateDiff;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class BasketService {

	private final BasketRepository baskets;
	private final BasketItemService items;
	private final MathProperties math;

	public BasketService(BasketRepository baskets, BasketItemService items, MathProperties math) {
		this.baskets = baskets;
		this.items = items;
		this.math = math;
	}

	public Basket create() {
		return baskets.save(new Basket());
	}

	public Basket findOne(long id) {
		return baskets.getOne(id);
	}

	public BasketUpdateDiff removeItem(long basketId, long itemId) {
		return updateBasket(basketId, () -> items.removeItem(basketId, itemId));
	}

	private BasketUpdateDiff updateBasket(long basketId, Supplier<BasketUpdateDiff> diffSupplier) {
		Basket basket = findOne(basketId);
		basket.assertNotCheckedOut();

		BasketUpdateDiff diff = diffSupplier.get();

		basket.update(diff, math);
		baskets.save(basket);

		return diff;
	}
}
