package br.com.alura.produto.domain.usecase;

import br.com.alura.produto.domain.entity.Producto;
import br.com.alura.produto.domain.repository.BucketRepository;
import br.com.alura.produto.domain.repository.ProductoRepository;
import br.com.alura.produto.domain.repository.QueueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static br.com.alura.produto.domain.entity.factory.ProductoFactory.crearProducto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class RegistroProductoUseCaseTest {

    @InjectMocks
    RegistroProductoUseCase registroProductoUseCase;

    @Mock
    ProductoRepository productoRepository;

    @Mock
    BucketRepository bucketRepository;

    @Mock
    QueueRepository queueRepository;

    @DisplayName("Cuando registrar un producto")
    @Nested
    class Registrar {

        @DisplayName("Entonces debe ejecutar con éxito")
        @Nested
        class Exito {

            @BeforeEach
            void beforeEach() {
                when(productoRepository.save(any()))
                        .thenAnswer(invocationOnMock -> {
                            Producto producto = invocationOnMock.getArgument(0);
                            setField(producto, "productoId", UUID.fromString("b34f8434-5dfb-4a3e-aa51-bff7ce7dd884"));
                            return producto;
                        });
            }

            @DisplayName("Dado un producto con todos los campos")
            @Test
            void prueba1() {
                var producto = crearProducto()
                        .conTodosLosCampos();
                var actual = registroProductoUseCase.registrar(producto);
                assertThat(actual.getProductoId())
                        .isNotNull();
            }

            @DisplayName("Dado un producto con todos los campos y con {status}")
            @ParameterizedTest
            @EnumSource(Producto.Status.class)
            void prueba2(Producto.Status status) {
                var producto = crearProducto()
                        .conTodosLosCampos();
                setField(producto, "status", status);
                var actual = registroProductoUseCase.registrar(producto);
                assertThat(actual.getStatus())
                        .isEqualTo(status);
            }
        }
    }
}