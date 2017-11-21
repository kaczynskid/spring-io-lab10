package com.example.store.basket.item;

import com.example.store.MathProperties;
import com.example.store.item.ItemClient;
import com.example.store.item.ItemRepresentation;
import com.example.store.special.SpecialCalculation;
import com.example.store.special.SpecialCalculationRequest;
import com.example.store.special.SpecialClient;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.store.special.SpecialCalculationRequest.requestCalculationFor;
import static java.math.BigDecimal.ZERO;

@Component
public class BasketItemService {

	private final BasketItemRepository basketItems;
	private final ItemClient items;
	private final SpecialClient specials;
	private final MathProperties math;

	public BasketItemService(BasketItemRepository basketItems, ItemClient items, SpecialClient specials, MathProperties math) {
		this.basketItems = basketItems;
		this.items = items;
		this.specials = specials;
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
		SpecialCalculation calculation = specials.calculateFor(basketItem.getItemId(), requestCalculationFor(count));
		BasketUpdateDiff diff = basketItem.update(changes, count, calculation, math);
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
