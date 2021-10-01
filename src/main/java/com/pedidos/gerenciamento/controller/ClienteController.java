package com.pedidos.gerenciamento.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.gerenciamento.model.Cliente;
import com.pedidos.gerenciamento.service.ClienteService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente criar(@RequestBody Cliente cliente) {
		return clienteService.createOrUpdate(cliente);
	}
	
	@GetMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente encontrarPeloId(@PathParam("id") Long id) {
		return clienteService.readById(id);
	}
	
	@GetMapping("/clientes")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Cliente> listarTudo() {
		return clienteService.readAll();
	}
	
	@PutMapping("/cliente")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	public Cliente atualizar(@RequestBody Cliente cliente) {
		return clienteService.createOrUpdate(cliente);
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePeloId(@PathParam("id") Long id) {
		clienteService.deleteById(id);
	}

}
