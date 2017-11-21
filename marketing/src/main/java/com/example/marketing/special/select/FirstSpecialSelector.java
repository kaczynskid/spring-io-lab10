package com.example.marketing.special.select;

import com.example.marketing.special.Special;
import com.example.marketing.special.calculate.SpecialCalculator;

import java.math.BigDecimal;
import java.util.List;

public class FirstSpecialSelector implements SpecialSelector {

	@Override
	public SpecialCalculator selectSpecial(List<Special> specials, int unitCount, BigDecimal unitPrice) {
		return specials.stream()
				.filter(s -> s.getBatchSize() <= unitCount)
				.map(s -> (SpecialCalculator) s)
				.findFirst()
				.orElse(SpecialCalculator.regularPrice);
	}
}
