package com.algawork.osworks.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawork.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar(){
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setName("João");
		cliente1.setEmail("joao@email.com");
		cliente1.setTelefone("35 912345678");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setName("Maria");
		cliente2.setEmail("maeia@email.com");
		cliente2.setTelefone("77 912365678");

		clientes.add(cliente1);
		clientes.add(cliente2);
		
		return clientes;
	}
}
