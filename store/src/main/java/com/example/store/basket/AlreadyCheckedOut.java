package com.example.store.basket;

class AlreadyCheckedOut extends RuntimeException {

	AlreadyCheckedOut(Basket basket) {
		super(String.format("Basket %d is already checked out", basket.getId()));
	}
}

