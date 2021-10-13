package com.pedidos.gerenciamento.model;

import java.io.Serializable;
import java.time.LocalDate;

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
public class Cliente implements Serializable {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -2349364649152559589L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30)
	private String nome;
	@Column(length = 20)
	private String cpf;
	private LocalDate dataDeNascimento;

}
