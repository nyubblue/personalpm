package io.ubun.personpm.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ValidationBodyService {

	public ValidationBodyService() {
	}

	public ResponseEntity<?> validate(BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> errMap = new HashMap<>();
			result.getFieldErrors().stream().forEach(fieldErr -> {
				errMap.put(fieldErr.getField(), fieldErr.getDefaultMessage());
			});
			return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
