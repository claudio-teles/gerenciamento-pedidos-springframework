package com.pedidos.gerenciamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.gerenciamento.model.Cliente;
import com.pedidos.gerenciamento.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar cliente")
	public Cliente criar(@RequestBody Cliente cliente) {
		return clienteService.createOrUpdate(cliente);
	}
	
	@GetMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Encontrar cliente pelo id")
	public Cliente encontrarPeloId(@PathVariable("id") Long id) {
		return clienteService.readById(id);
	}
	
	@GetMapping("/clientes")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Listar todos os clientes")
	public Iterable<Cliente> listarTudo() {
		return clienteService.readAll();
	}
	
	@PutMapping("/cliente")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@ApiOperation(value = "Atualizar um cliente já cadastrado")
	public Cliente atualizar(@RequestBody Cliente cliente) {
		return clienteService.createOrUpdate(cliente);
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Deletar um cliente")
	public void deletePeloId(@PathVariable("id") Long id) {
		clienteService.deleteById(id);
	}

}
