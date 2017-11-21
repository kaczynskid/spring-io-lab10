package com.example.marketing.special.select;

import com.example.marketing.MathProperties;
import com.example.marketing.special.Special;
import com.example.marketing.special.calculate.SpecialCalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class BestSpecialSelector implements SpecialSelector {

	private final MathProperties math;

	public BestSpecialSelector(MathProperties math) {
		this.math = math;
	}

	@Override
	public SpecialCalculator selectSpecial(List<Special> specials, int unitCount, BigDecimal unitPrice) {
		return Stream.concat(
				Stream.of(SpecialCalculator.regularPrice),
				specials.stream()
					.filter(s -> s.getBatchSize() <= unitCount)
					.map(s -> (SpecialCalculator) s))
				.sorted(comparing(s -> s.calculateFor(unitCount, unitPrice, math).getTotalPrice()))
				.findFirst()
				.get();
	}
}
