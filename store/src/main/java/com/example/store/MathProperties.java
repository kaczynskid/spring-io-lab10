package com.example.store;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.MathContext;

import static java.math.RoundingMode.HALF_EVEN;

@Data
@Component
public class MathProperties {

	/** Precision for Math operations */
	private int precision = 18;

	/** Scale for Math operations */
	private int scale = 4;

	public MathContext getContext() {
		return new MathContext(precision, HALF_EVEN);
	}
}
