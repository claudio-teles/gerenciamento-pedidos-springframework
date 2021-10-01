package com.pedidos.gerenciamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.gerenciamento.model.Produto;
import com.pedidos.gerenciamento.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto createOrUpdate(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto readById(Long id) {
		return produtoRepository.findById(id).get();
	}
	
	public Iterable<Produto> readAll() {
		return produtoRepository.findAll();
	}
	
	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}

}
