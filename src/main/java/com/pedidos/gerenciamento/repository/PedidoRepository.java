package com.pedidos.gerenciamento.repository;

import org.springframework.data.repository.CrudRepository;

import com.pedidos.gerenciamento.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
