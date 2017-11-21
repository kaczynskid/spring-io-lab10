package com.example.marketing.special;

import com.example.marketing.special.calculate.SpecialCalculation;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/specials")
public class SpecialController {

	private final SpecialService specials;

	public SpecialController(SpecialService specials) {
		this.specials = specials;
	}

	@GetMapping
	public List<SpecialRepresentation> findAll() {
		return specials.findAll().stream()
				.map(SpecialRepresentation::of)
				.collect(toList());
	}

	@PostMapping
	public SpecialRepresentation add(@RequestBody SpecialRepresentation request) {
		return SpecialRepresentation.of(specials.create(request.asSpecial()));
	}

	@PostMapping("/{itemId}/calculate")
	public SpecialCalculation calculateFor(@PathVariable("itemId") long itemId,
										   @RequestBody SpecialCalculationRequest request) {
		return specials.calculateFor(itemId, request.getUnitPrice(), request.getUnitCount());
	}
}

@Data
class SpecialCalculationRequest {

	private BigDecimal unitPrice;
	private int unitCount;
}
