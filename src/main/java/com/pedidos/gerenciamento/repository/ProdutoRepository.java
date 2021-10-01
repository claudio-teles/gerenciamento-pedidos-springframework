package com.pedidos.gerenciamento.repository;

import org.springframework.data.repository.CrudRepository;

import com.pedidos.gerenciamento.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
