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

import com.pedidos.gerenciamento.model.Produto;
import com.pedidos.gerenciamento.service.ProdutoService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE })
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping("/produto")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar um produto")
	public Produto criar(@RequestBody Produto produto) {
		return produtoService.createOrUpdate(produto);
	}

	@GetMapping("/produto/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Localizar um Produto")
	public Produto encontrarPeloId(@PathVariable("id") Long id) {
		return produtoService.readById(id);
	}

	@GetMapping("/produtos")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Listar todos os produtos")
	public Iterable<Produto> listarTudo() {
		return produtoService.readAll();
	}

	@PutMapping("/produto")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@ApiOperation(value = "Atualizar um produto")
	public Produto atualizar(@RequestBody Produto produto) {
		return produtoService.createOrUpdate(produto);
	}

	@DeleteMapping("/produto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Deletar um produto")
	public void deletePeloId(@PathVariable("id") Long id) {
		produtoService.deleteById(id);
	}

}
