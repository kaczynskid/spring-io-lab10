package com.example.store.item;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRepresentation {

	private String name;

	private BigDecimal price;
}
