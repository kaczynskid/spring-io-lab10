package com.example.store.basket.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

interface BasketItemRepository extends JpaRepository<BasketItem, Long> {

	Stream<BasketItem> streamByBasketId(long basketId);

	List<BasketItem> findByBasketId(long basketId);

	Optional<BasketItem> findByBasketIdAndItemId(long basketId, long itemId);
}
