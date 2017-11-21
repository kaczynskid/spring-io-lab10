package com.example.store.item;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ItemService {

	private final ItemRepository items;

	public ItemService(ItemRepository items) {
		this.items = items;
	}

	public List<Item> findAll() {
		return items.findAll();
	}

	public Item findOne(Long id) {
		return items.getOne(id);
	}

	public Item create(Item item) {
		return items.save(item);
	}

	public Item update(ItemUpdate changes) {
		Assert.notNull(changes, "Changes cannot be null");

		Item item = findOne(changes.getId());
		item.update(changes);
		return items.save(item);
	}

	public Item updateStock(ItemStockUpdate changes) {
		Assert.notNull(changes, "Changes cannot be null");

		Item item = findOne(changes.getId());
		item.updateStock(changes);
		return items.save(item);
	}
}

