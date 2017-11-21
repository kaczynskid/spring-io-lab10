package com.example.store.basket.item;

import com.example.store.MathProperties;
import com.example.store.item.ItemClient;
import com.example.store.item.ItemRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.math.BigDecimal.ZERO;

@Component
public class BasketItemService {

	private final BasketItemRepository basketItems;
	private ItemClient items;
	private final MathProperties math;

	public BasketItemService(BasketItemRepository basketItems, ItemClient items, MathProperties math) {
		this.basketItems = basketItems;
		this.items = items;
		this.math = math;
	}

	public List<BasketItem> findAllItems(long basketId) {
		return basketItems.findByBasketId(basketId);
	}

	public BasketItem findOneItem(long basketId, long itemId) {
		return basketItems.findByBasketIdAndItemId(basketId, itemId).orElse(null);
	}

	public BasketUpdateDiff updateItem(long basketId, long itemId, int count) {
		BasketItem basketItem = basketItems.findByBasketIdAndItemId(basketId, itemId)
				.orElse(newBasketItem(basketId, itemId));

		if (count > 0) {
			return updateInBasket(basketItem, count);
		} else {
			return removeFromBasket(basketItem);
		}
	}

	public BasketUpdateDiff removeItem(long basketId, long itemId) {
		return basketItems.findByBasketIdAndItemId(basketId, itemId)
				.map(this::removeFromBasket)
				.orElse(new BasketUpdateDiff(0, ZERO));
	}

	private BasketUpdateDiff updateInBasket(BasketItem basketItem, int count) {
		ItemRepresentation changes = items.findOne(basketItem.getItemId());
		BasketUpdateDiff diff = basketItem.update(changes, count, math);
		basketItems.save(basketItem);

		return diff;
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
