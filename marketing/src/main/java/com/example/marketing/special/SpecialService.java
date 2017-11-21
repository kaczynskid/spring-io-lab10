package com.example.marketing.special;

import com.example.marketing.MathProperties;
import com.example.marketing.item.ItemClient;
import com.example.marketing.item.ItemRepresentation;
import com.example.marketing.special.calculate.SpecialCalculation;
import com.example.marketing.special.select.SpecialSelector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpecialService {

	private final SpecialRepository specials;
	private final SpecialSelector selector;
	private final ItemClient items;
	private final MathProperties math;

	public SpecialService(SpecialRepository specials, SpecialSelector selector, ItemClient items, MathProperties math) {
		this.specials = specials;
		this.selector = selector;
		this.items = items;
		this.math = math;
	}

	public Special findOne(String id) {
		return specials.findOne(id);
	}

	public List<Special> findAll() {
		return specials.findAll();
	}

	public List<Special> findByItemId(long itemId) {
		return specials.findByItemId(itemId);
	}

	public Special create(Special special) {
		return specials.save(special);
	}

	public SpecialCalculation calculateFor(long itemId, int unitCount) {
		ItemRepresentation item = items.findOne(itemId);
		List<Special> candidates = specials.findByItemIdAndBatchSizeLessThanEqual(itemId, unitCount);
		return selector.selectSpecial(candidates, unitCount, item.getPrice())
				.calculateFor(unitCount, item.getPrice(), math);
	}
}
