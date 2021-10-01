package com.pedidos.gerenciamento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.time.LocalDate;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
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
import com.pedidos.gerenciamento.service.ClienteService;

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
						.get("/cliente/{id", 1L)
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

}
