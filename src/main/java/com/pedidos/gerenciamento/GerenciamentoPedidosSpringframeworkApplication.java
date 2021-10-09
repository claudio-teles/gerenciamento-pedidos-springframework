package com.pedidos.gerenciamento;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.pedidos.gerenciamento.model.Cliente;
import com.pedidos.gerenciamento.model.Produto;
import com.pedidos.gerenciamento.repository.ClienteRepository;
import com.pedidos.gerenciamento.repository.ProdutoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GerenciamentoPedidosSpringframeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoPedidosSpringframeworkApplication.class, args);
	}

	@Bean
	public CommandLineRunner seed(ProdutoRepository pr, ClienteRepository cr) {
	  return (args) -> {
		  pr.save(new Produto(null, "Arroz", "Arroz Parboilizado", new BigDecimal("4.6"), 12)); // 1
		  pr.save(new Produto(null, "Feijão", "Feijão Carioca", new BigDecimal("3.8"), 20)); // 2
		  pr.save(new Produto(null, "Carne", "Carne Moida", new BigDecimal("5.2"), 15)); // 3
		  pr.save(new Produto(null, "Miojo", "Lámem", new BigDecimal("1.676"), 120)); // 4
		  pr.save(new Produto(null, "Linguiça", "Linguiça De Porco", new BigDecimal("3.9"), 18)); // 5
		  pr.save(new Produto(null, "Óleo", "Óleo De Soja", new BigDecimal("3.57"), 22)); // 6
		  pr.save(new Produto(null, "Refrigerante", "Coca Cola", new BigDecimal("7.48"), 19)); // 7
		  pr.save(new Produto(null, "Salsicha", "Pacote 30 Unidades", new BigDecimal("8.72"), 15)); // 8
		  pr.save(new Produto(null, "Café", "Café Em Grãos", new BigDecimal("5.87"), 21)); // 9
		  pr.save(new Produto(null, "Arroz", "Arroz Integral", new BigDecimal("5.3"), 26)); // 10

		  pr.save(new Produto(null, "Peixe", "Bacalhau", new BigDecimal("9.45"), 13)); // 11
		  pr.save(new Produto(null, "Sardinha", "Sardinha Pacote 8 Unids.", new BigDecimal("12.7"), 19)); // 12
		  pr.save(new Produto(null, "Sal", "Sal Pacote 3 Kg", new BigDecimal("1.7"), 15)); // 13
		  pr.save(new Produto(null, "Tomate", "Duzia", new BigDecimal("7.5"), 30)); // 14
		  pr.save(new Produto(null, "Alface", "Pacote 6 Feixes", new BigDecimal("3.89"), 17)); // 15
		  pr.save(new Produto(null, "Laranja", "Laranja Casca Amarela", new BigDecimal("4.8"), 18)); // 16
		  pr.save(new Produto(null, "Mamão", "Mamão Papaia", new BigDecimal("3.7"), 16)); // 17
		  pr.save(new Produto(null, "Salsinhha", "100 Gramas", new BigDecimal("2.9"), 17)); // 18
		  pr.save(new Produto(null, "Leite", "Leite Em Pó", new BigDecimal("4.8"), 11)); // 19
		  pr.save(new Produto(null, "Chocolate Em Pó", "Chocolate Em Pó Com Açucar", new BigDecimal("4.6"), 18)); // 20

		  cr.save(new Cliente(null, "Antônio Feitosa Bastos", "786.092.986-48", LocalDate.of(1998, 2, 17))); // 1
		  cr.save(new Cliente(null, "Maria Augusta Siqueira", "765.092.986-84", LocalDate.of(1993, 3, 26))); // 2
		  cr.save(new Cliente(null, "Miguel Neto Da Silva", "123.098.986-38", LocalDate.of(1998, 9, 12))); // 3
		  cr.save(new Cliente(null, "Suzana Junqueira Medeiros", "456.096.129-96", LocalDate.of(1988, 9, 28))); // 4
		  cr.save(new Cliente(null, "João Ferreira Filho", "817.048.938-72", LocalDate.of(1979, 5, 11))); // 5
	  };

	}

}
