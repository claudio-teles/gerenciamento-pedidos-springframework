package com.pedidos.gerenciamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.gerenciamento.model.Pedido;
import com.pedidos.gerenciamento.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido createOrUpdate(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido readById(Long id) {
		return pedidoRepository.findById(id).get();
	}
	
	public Iterable<Pedido> readAll() {
		return pedidoRepository.findAll();
	}
	
	public void deleteById(Long id) {
		pedidoRepository.deleteById(id);
	}

}
