package com.pedidos.gerenciamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.gerenciamento.model.Cliente;
import com.pedidos.gerenciamento.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente createOrUpdate(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente readById(Long id) {
		return clienteRepository.findById(id).get();
	}
	
	public Iterable<Cliente> readAll() {
		return clienteRepository.findAll();
	}
	
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

}
