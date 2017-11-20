package com.example.store.item;

import java.util.List;

interface ItemRepository {

	List<Item> findAll();

	Item getOne(Long id);

	Item save(Item item);
}
