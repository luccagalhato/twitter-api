package br.com.lucca.twitterapi.exceptions;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public HashMap<String, String> manipulation422(MethodArgumentNotValidException exception) {
		HashMap<String, String> erros = new HashMap<>();
		BindingResult resultado = exception.getBindingResult();

		List<FieldError> fieldErrors = resultado.getFieldErrors();

		for (FieldError erro : fieldErrors) {
			erros.put(erro.getField(), erro.getDefaultMessage());
		}

		return erros;
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	@ResponseBody
	public HashMap<String, String> manipulation403(HttpStatusCodeException exception) {
		HashMap<String, String> erros = new HashMap<>();
		erros.put("message", exception.getStatusText());
		return erros;
	}

}
