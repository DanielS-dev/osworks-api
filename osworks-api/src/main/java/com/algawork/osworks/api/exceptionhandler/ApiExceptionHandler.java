package com.algawork.osworks.api.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algawork.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algawork.osworks.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	ProblemaInstancia problemaInstancia = new ProblemaInstancia();
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handlNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		String titulo = ex.getMessage();
		Problema problema = problemaInstancia.InstanciarProblema(status.value(), titulo);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handlNegocio(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		String titulo = ex.getMessage();
		Problema problema = problemaInstancia.InstanciarProblema(status.value(), titulo);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problema.Campo> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			String nome = ((FieldError)error).getField();
			
			campos.add(new Problema.Campo(nome, mensagem));
		}
		
		String titulo = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";
		Problema problema = problemaInstancia.InstanciarProblema(status.value(), titulo, campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
}
