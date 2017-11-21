package com.example.marketing.special.select;

import com.example.marketing.special.Special;
import com.example.marketing.special.calculate.SpecialCalculator;

import java.math.BigDecimal;
import java.util.List;

@FunctionalInterface
public interface SpecialSelector {

	SpecialCalculator selectSpecial(List<Special> specials, int unitCount, BigDecimal unitPrice);
}
