package com.pedidos.gerenciamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedidos.gerenciamento.model.Cliente;
import com.pedidos.gerenciamento.model.Pedido;
import com.pedidos.gerenciamento.model.Produto;
import com.pedidos.gerenciamento.service.ClienteService;
import com.pedidos.gerenciamento.service.PedidoService;
import com.pedidos.gerenciamento.service.ProdutoService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles(value = "test")
class GerenciamentoPedidosSpringframeworkApplicationTests {
	
	@LocalServerPort
	private int port;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private PedidoService pedidoService;

	@Test
	@Order(1)
	void salvarClienteControllerTest() {
		Cliente cliente1 = Cliente.builder()
				.nome("José Alves Feitosa")
				.cpf("793.920.152-94")
				.dataDeNascimento(LocalDate.of(1979, 3, 15))
				.build();
		
		Cliente cliente2 = Cliente.builder()
				.nome("Antônia Lima Barros")
				.cpf("903.920.852-14")
				.dataDeNascimento(LocalDate.of(1991, 8, 7))
				.build();
		
		Cliente cliente3 = Cliente.builder()
										.nome("Marcos Freitas de Sá")
										.cpf("129.563.152-69")
										.dataDeNascimento(LocalDate.of(2007, 9, 1))
									.build();
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.post("/cliente")
						.content(objectMapper.writeValueAsString(cliente1))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.nome", CoreMatchers.is("José Alves Feitosa")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		clienteService.createOrUpdate(cliente2);
		clienteService.createOrUpdate(cliente3);
	}
	
	@Test
	@Order(2)
	void encontrarClientePeloIdControllerTest() {
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.get("/cliente/{id}", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.nome", CoreMatchers.is("José Alves Feitosa")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void listarClienteControllerTest() {
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.get("/clientes")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	void atualizarCpfClienteControllerTest() {
		// cpf antigo -> 793.920.152-94
		Cliente cliente = new Cliente();
		cliente = clienteService.readById(1L);
		cliente.setCpf("104.920.152-83");//cpf novo 
		
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.put("/cliente")
						.content(objectMapper.writeValueAsString(cliente))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isPartialContent())
			.andExpect(MockMvcResultMatchers.jsonPath("$.cpf", CoreMatchers.is("104.920.152-83")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(5)
	void deletarClienteIdControllerTest() {
		try {
			this.mockMvc
			.perform(
						MockMvcRequestBuilders
							.delete("/cliente/{id}", 3L)
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON)
					)
					.andExpect(MockMvcResultMatchers.status().isNoContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(6)
	void salvarProdutoControllerTest() {
		Produto produto1 = Produto.builder()
				.nome("Arroz")
				.descricao("Produto Integral")
				.preco(new BigDecimal("4.80"))
				.quantidade(10)
				.build();
		
		Produto produto2 = Produto.builder()
				.nome("Feijão")
				.descricao("Carioca")
				.preco(new BigDecimal("3.75"))
				.quantidade(10)
				.build();
		
		Produto produto3 = Produto.builder()
				.nome("Macarrão")
				.descricao("Miojo")
				.preco(new BigDecimal("1.92"))
				.quantidade(5)
				.build();
		
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
					.post("/produto")
					.content(objectMapper.writeValueAsString(produto1))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.descricao", CoreMatchers.is("Produto Integral")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		produtoService.createOrUpdate(produto2);
		produtoService.createOrUpdate(produto3);
	}
	
	@Test
	@Order(7)
	void encontrarProdutoPeloIdControllerTest() {
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
					.get("/produto/{id}", 5L)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.descricao", CoreMatchers.is("Carioca")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(8)
	void listarProdutoControllerTest() {
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
					.get("/produtos")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(9)
	void atualizarProdutoControllerTest() {
		Produto produto3 = produtoService.readById(4L);
		produto3.setDescricao("Parboilizado");
		
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
					.put("/produto")
					.content(objectMapper.writeValueAsString(produto3))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isPartialContent())
			.andExpect(MockMvcResultMatchers.jsonPath("$.descricao", CoreMatchers.is("Parboilizado")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(10)
	void deletarProdutoIdControllerTest() {
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
					.delete("/produto/{id}", 6L)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isNoContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(11)
	void salvarPedidoControllerTest() {
		List<Produto> carrinhoDeCompras1 = new ArrayList<>();
		Pedido pedido1 = Pedido.builder()
				.cliente(clienteService.readById(2L))
				.dataDaCompra(LocalDate.of(2020, 4, 12))
				.totalDaCompra(new BigDecimal("37.5"))
				.produtos(carrinhoDeCompras1)
				.build();
		
		List<Produto> carrinhoDeCompras2 = new ArrayList<>();
		Pedido pedido2 = Pedido.builder()
				.cliente(clienteService.readById(1L))
				.dataDaCompra(LocalDate.of(2021, 10, 7))
				.totalDaCompra(new BigDecimal("48.0"))
				.produtos(carrinhoDeCompras2)
				.build();
		
		List<Produto> carrinhoDeCompras3 = new ArrayList<>();
		Pedido pedido3 = Pedido.builder()
							.cliente(clienteService.readById(2L))
							.dataDaCompra(LocalDate.of(2021, 1, 18))
							.totalDaCompra(new BigDecimal("37.5"))
							.produtos(carrinhoDeCompras3)
							.build();
		
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.post("/pedido")
						.content(objectMapper.writeValueAsString(pedido1))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pedidoService.createOrUpdate(pedido2);
		pedidoService.createOrUpdate(pedido3);
	}
	
	@Test
	@Order(12)
	void encontrarPedidoPeloIdControllerTest() {
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.get("/pedido/{id}", 7L)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(13)
	void listarPedidoControllerTest() {
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.get("/pedidos")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(14)
	void atualizarPedidoControllerTest() {
		List<Produto> carrinho = new ArrayList<>();
		carrinho.add(produtoService.readById(4L));
		carrinho.add(produtoService.readById(5L));
		
		Pedido pedido3 = pedidoService.readById(9L);
		pedido3.setProdutos(carrinho);
		
		try {
			this.mockMvc
			.perform(
					MockMvcRequestBuilders
						.put("/pedido")
						.content(objectMapper.writeValueAsString(pedido3))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(MockMvcResultMatchers.status().isPartialContent())
			.andExpect(MockMvcResultMatchers.jsonPath("$.produtos", Matchers.hasSize(2)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(15)
	void deletarPedidoIdControllerTest() {
		try {
			this.mockMvc
			.perform(
						MockMvcRequestBuilders
							.delete("/pedido/{id}", 8L)
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON)
					)
					.andExpect(MockMvcResultMatchers.status().isNoContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
