package com.example.store.item;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.util.ReflectionUtils.*;

@Component
class InMemoryItemRepository implements ItemRepository {

	private final AtomicLong seq = new AtomicLong();
	private final List<Item> db = new LinkedList<>();

	@Override
	public List<Item> findAll() {
		return new ArrayList<>(db);
	}

	@Override
	public Item getOne(Long id) {
		return db.stream()
				.filter(item -> item.getId() == id).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public Item save(Item item) {
		if (item.getId() == null) {
			setId(seq.incrementAndGet(), item);
			db.add(item);
		} else {
			db.set(db.indexOf(getOne(item.getId())), item);
		}
		return item;
	}

	private void setId(long id, Item item) {
		Field idField = findField(Item.class, "id", Long.class);
		makeAccessible(idField);
		setField(idField, item, id);
	}
}
