package com.example.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import static com.example.store.ErrorMessage.messageResponseOf;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handle(EntityNotFoundException e) {
		return messageResponseOf(NOT_FOUND, "Not found");
	}
}

@Slf4j
@RestController
class DefaultErrorController extends AbstractErrorController {

	private final ErrorProperties errorProperties;

	public DefaultErrorController(ServerProperties serverProperties, ErrorAttributes errorAttributes) {
		super(errorAttributes);
		this.errorProperties = serverProperties.getError();
	}

	@Override
	public String getErrorPath() {
		return errorProperties.getPath();
	}

	@GetMapping("${server.error.path:${error.path:/error}}")
	public ResponseEntity<ErrorMessage> handle(HttpServletRequest request) {
		return messageResponseOf(INTERNAL_SERVER_ERROR, "Unexpected error");
	}
}
