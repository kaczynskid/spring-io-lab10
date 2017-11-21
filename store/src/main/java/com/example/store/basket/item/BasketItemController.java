package com.example.store.basket.item;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/baskets/{basketId}/items")
public class BasketItemController {

	private BasketItemService basketItems;

	public BasketItemController(BasketItemService basketItems) {
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
	public BasketItemRepresentation updateItem(@PathVariable("basketId") long basketId, @PathVariable("itemId") long itemId,
                                         @RequestBody BasketUpdateDiff request) {
		return null;
	}
}
