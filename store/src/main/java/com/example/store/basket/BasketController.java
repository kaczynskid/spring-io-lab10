package com.example.store.basket;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
public class BasketController {

	private final BasketService baskets;

	public BasketController(BasketService baskets) {
		this.baskets = baskets;
	}

	@PostMapping
	public BasketRepresentation create() {
		return BasketRepresentation.of(baskets.create());
	}

	@GetMapping("/{id}")
	public BasketRepresentation findOne(@PathVariable("id") long id) {
		return BasketRepresentation.of(baskets.findOne(id));
	}
}
