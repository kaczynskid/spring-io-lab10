package com.example.store.special;

import com.example.store.item.ItemClient;
import com.example.store.item.ItemRepresentation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpecialClientFallback implements SpecialClient {

    private ItemClient items;

    public SpecialClientFallback(ItemClient items) {
        this.items = items;
    }

    @Override
    public SpecialCalculation calculateFor(long itemId, SpecialCalculationRequest request) {
        ItemRepresentation item = items.findOne(itemId);
        return new SpecialCalculation(null, item.getPrice().multiply(BigDecimal.valueOf(request.getUnitCount())));
    }
}
