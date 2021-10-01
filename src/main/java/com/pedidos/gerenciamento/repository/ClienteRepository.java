package com.pedidos.gerenciamento.repository;

import org.springframework.data.repository.CrudRepository;

import com.pedidos.gerenciamento.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
