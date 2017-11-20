package com.example.store.item;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/items")
class ItemController {

	private final ItemService items;

	public ItemController(ItemService items) {
		this.items = items;
	}

	@GetMapping
	public List<ItemRepresentation> findAll() {
		return items.findAll().stream().map(ItemRepresentation::of).collect(toList());
	}

	@PostMapping
	public ItemRepresentation create(@RequestBody ItemRepresentation request) {
		return ItemRepresentation.of(items.create(request.asItem()));
	}

	@GetMapping("/{id}")
	public ItemRepresentation findOne(@PathVariable("id") long id) {
		return ItemRepresentation.of(items.findOne(id));
	}

	@PutMapping("/{id}")
	public ItemRepresentation update(@PathVariable("id") long id, @RequestBody ItemUpdate changes) {
		return ItemRepresentation.of(items.update(changes.withId(id)));
	}

	@PutMapping("/{id}/stock")
	public ItemRepresentation updateStock(@PathVariable("id") long id, @RequestBody ItemStockUpdate changes) {
		return ItemRepresentation.of(items.updateStock(changes.withId(id)));
	}
}
