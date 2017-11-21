package com.example.store.item;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "warehouse")
public interface ItemClient {

	@GetMapping("/items/{id}")
	ItemRepresentation findOne(@PathVariable("id") long id);
}
