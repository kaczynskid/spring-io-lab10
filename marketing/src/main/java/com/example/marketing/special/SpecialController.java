package com.example.marketing.special;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/specials")
public class SpecialController {

	private final SpecialService specials;

	public SpecialController(SpecialService specials) {
		this.specials = specials;
	}

	@GetMapping
	public List<SpecialRepresentation> findAll(@RequestParam(required = false) Long itemId) {
		return ofNullable(itemId)
				.map(specials::findByItemId)
				.orElse(specials.findAll()).stream()
				.map(SpecialRepresentation::of)
				.collect(Collectors.toList());
	}

	@PostMapping
	public SpecialRepresentation add(@RequestBody SpecialRepresentation request) {
		return SpecialRepresentation.of(specials.create(request.asSpecial()));
	}
}
