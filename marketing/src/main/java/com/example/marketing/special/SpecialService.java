package com.example.marketing.special;

import com.example.marketing.MathProperties;
import com.example.marketing.special.calculate.SpecialCalculation;
import com.example.marketing.special.select.SpecialSelector;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SpecialService {

	private final SpecialRepository specials;
	private final SpecialSelector selector;
	private final MathProperties math;

	public SpecialService(SpecialRepository specials, SpecialSelector selector, MathProperties math) {
		this.specials = specials;
		this.selector = selector;
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

	public SpecialCalculation calculateFor(long itemId, BigDecimal unitPrice, int unitCount) {
		List<Special> candidates = specials.findByItemIdAndBatchSizeLessThanEqual(itemId, unitCount);
		return selector.selectSpecial(candidates, unitCount, unitPrice)
				.calculateFor(unitCount, unitPrice, math);
	}
}
