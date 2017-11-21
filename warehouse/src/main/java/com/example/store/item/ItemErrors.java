package com.example.store.item;

import com.example.store.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.store.ErrorMessage.messageResponseOf;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ItemErrors {

	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handle(OutOfStock e) {
		return messageResponseOf(BAD_REQUEST, e.getMessage());
	}
}

class OutOfStock extends RuntimeException {

	OutOfStock(Item item, int count) {
		super(String.format("Item %s has only %d out of %d requested", item.getName(), item.getCount(), count));
	}
}
