package com.example.store.basket.item;

import com.example.store.MathProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.math.BigDecimal.ZERO;

@Component
public class BasketItemService {

	private final BasketItemRepository basketItems;
	private final MathProperties math;

	public BasketItemService(BasketItemRepository basketItems, MathProperties math) {
		this.basketItems = basketItems;
		this.math = math;
	}

	public List<BasketItem> findAllItems(long basketId) {
		return basketItems.findByBasketId(basketId);
	}

	public BasketItem findOneItem(long basketId, long itemId) {
		return basketItems.findByBasketIdAndItemId(basketId, itemId).orElse(null);
	}

	public BasketUpdateDiff removeItem(long basketId, long itemId) {
		return basketItems.findByBasketIdAndItemId(basketId, itemId)
				.map(this::removeFromBasket)
				.orElse(new BasketUpdateDiff(0, ZERO));
	}

	private BasketUpdateDiff removeFromBasket(BasketItem basketItem) {
		BasketUpdateDiff diff = BasketUpdateDiff.ofItem(basketItem);
		basketItems.delete(basketItem);

		return diff;
	}

	private static BasketItem newBasketItem(long basketId, long itemId) {
		return new BasketItem(basketId, itemId);
	}
}
