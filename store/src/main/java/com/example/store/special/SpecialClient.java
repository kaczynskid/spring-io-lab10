package com.example.store.special;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "marketing", fallback = SpecialClientFallback.class)
public interface SpecialClient {

    @PostMapping("/specials/{itemId}/calculation")
    SpecialCalculation calculateFor(@PathVariable("itemId") long itemId, @RequestBody SpecialCalculationRequest request);

}
