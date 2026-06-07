package br.com.alura.produto.application.v1.controller;

import br.com.alura.produto.application.v1.config.RestControllerTestConfig;
import br.com.alura.produto.domain.usecase.RegistroProductoUseCase;
import br.com.alura.produto.domain.usecase.ConsultaProductoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.alura.produto.application.v1.dto.ProductoDtoFactory.crearProductoDtoRequest;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(classes = ProductoController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = RestControllerTestConfig.class)
class ProductoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    RegistroProductoUseCase registroProductoUseCase;

    @MockitoBean
    ConsultaProductoUseCase consultaProductoUseCase;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("Cuando consumir POST /v1/productos")
    @Nested
    class RegistrarProducto {

        @DisplayName("Entonces debe ejecutar con éxito")
        @Nested
        class Exito {

            @BeforeEach
            void beforeEach() {
                when(registroProductoUseCase.registrar(any()))
                        .thenAnswer(invocationOnMock -> {
                            var producto = invocationOnMock.getArgument(0);
                            setField(producto, "productoId", UUID.fromString("c06de587-4b79-49e7-8c02-aa0aecfec574"));
                            return producto;
                        });
            }

            @DisplayName("Dado un producto con todos los campos")
            @Test
            void prueba1() throws Exception {
                // Dado
                var producto = crearProductoDtoRequest()
                        .conTodosLosCampos();
                // Cuando
                mockMvc.perform(post("/v1/productos")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                                .characterEncoding(UTF_8.name())
                                .content(objectMapper.writeValueAsString(producto)))
                        // Entonces
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.productoId", is("c06de587-4b79-49e7-8c02-aa0aecfec574")))
                ;
            }
        }

        @DisplayName("Entonces retornar error")
        @Nested
        class Falla {

            @DisplayName("Dado un producto con valor menor a 1.99")
            @Test
            void prueba1() throws Exception {
                // Dado
                var producto = crearProductoDtoRequest()
                        .conTodosLosCampos();
                setField(producto, "valor", new BigDecimal("1.98"));
                // Cuando
                mockMvc.perform(post("/v1/productos")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                                .characterEncoding(UTF_8.name())
                                .content(objectMapper.writeValueAsString(producto)))
                        // Entonces
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().string("[valor] debe ser mayor o igual a $ 1.99"))
                ;
            }
        }
    }
}