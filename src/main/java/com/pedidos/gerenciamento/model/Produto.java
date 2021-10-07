package com.pedidos.gerenciamento.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2897366141380781760L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 40)
	private String nome;
	@Column(length = 40)
	private String descricao;
	private BigDecimal preco;
	private int quantidade;

}
