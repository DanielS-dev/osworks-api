package com.algawork.osworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.algawork.osworks.api.exceptionhandler.Problema.Campo;

public class ProblemaInstancia {

	Problema problema = new Problema();

	public Problema InstanciarProblema(int status, String titulo) {

		problema.setStatus(status);
		problema.setTitulo(titulo);
		problema.setDatahora(OffsetDateTime.now());
		
		return problema;
	}

	public Problema InstanciarProblema(int status, String titulo, List<Campo> campos) {

		problema.setStatus(status);
		problema.setTitulo(titulo);
		problema.setDatahora(OffsetDateTime.now());
		problema.setCampos(campos);
		
		return problema;
	}

}
