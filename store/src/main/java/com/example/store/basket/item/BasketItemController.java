package com.example.store.basket.item;

import com.example.store.basket.BasketService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/baskets/{basketId}/items")
public class BasketItemController {

	private BasketService basket;
	private BasketItemService basketItems;

	public BasketItemController(BasketService basket, BasketItemService basketItems) {
		this.basket = basket;
		this.basketItems = basketItems;
	}

	@GetMapping
	public List<BasketItemRepresentation> findAll(@PathVariable("basketId") long basketId) {
		return basketItems.findAllItems(basketId).stream()
				.map(BasketItemRepresentation::of)
				.collect(Collectors.toList());
	}

	@GetMapping("/{itemId}")
	public BasketItemRepresentation getItem(@PathVariable("basketId") long basketId, @PathVariable("itemId") long itemId) {
		return BasketItemRepresentation.of(basketItems.findOneItem(basketId, itemId));
	}

	@PutMapping("/{itemId}")
	public BasketUpdateDiff updateItem(@PathVariable("basketId") long basketId, @PathVariable("itemId") long itemId,
                                         @RequestBody UpdateBasketItem request) {
		return basket.updateItem(basketId, itemId, request.getItemCount());
}
}

@Data
class UpdateBasketItem {

	private int itemCount;
}
