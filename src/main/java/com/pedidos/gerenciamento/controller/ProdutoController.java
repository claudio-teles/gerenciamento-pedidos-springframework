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

import com.pedidos.gerenciamento.model.Produto;
import com.pedidos.gerenciamento.service.ProdutoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping("/produto")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto criar(@RequestBody Produto produto) {
		return produtoService.createOrUpdate(produto);
	}

	@GetMapping("/produto/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Produto encontrarPeloId(@PathParam("id") Long id) {
		return produtoService.readById(id);
	}

	@GetMapping("/produtos")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Produto> listarTudo() {
		return produtoService.readAll();
	}

	@PutMapping("/produto")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	public Produto atualizar(@RequestBody Produto produto) {
		return produtoService.createOrUpdate(produto);
	}

	@DeleteMapping("/produto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePeloId(@PathParam("id") Long id) {
		produtoService.deleteById(id);
	}

}
