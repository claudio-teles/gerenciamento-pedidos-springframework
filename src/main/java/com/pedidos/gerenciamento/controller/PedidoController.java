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

import com.pedidos.gerenciamento.model.Pedido;
import com.pedidos.gerenciamento.service.PedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE })
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("/pedido")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar um pedido")
	public Pedido criar(@RequestBody Pedido pedido) {
		return pedidoService.createOrUpdate(pedido);
	}

	@GetMapping("/pedido/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Localizar um pedido pelo id")
	public Pedido encontrarPeloId(@PathVariable("id") Long id) {
		return pedidoService.readById(id);
	}

	@GetMapping("/pedidos")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Litstar todos os pedidos")
	public Iterable<Pedido> listarTudo() {
		return pedidoService.readAll();
	}

	@PutMapping("/pedido")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@ApiOperation(value = "Atualizar um pedido")
	public Pedido atualizar(@RequestBody Pedido pedido) {
		return pedidoService.createOrUpdate(pedido);
	}

	@DeleteMapping("/pedido/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Deletar um pedido")
	public void deletePeloId(@PathVariable("id") Long id) {
		pedidoService.deleteById(id);
	}

}
