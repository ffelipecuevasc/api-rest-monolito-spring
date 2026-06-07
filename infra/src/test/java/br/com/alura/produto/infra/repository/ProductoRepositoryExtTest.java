package br.com.alura.produto.infra.repository;

import br.com.alura.produto.domain.repository.ProductoRepository;
import br.com.alura.produto.infra.config.JpaConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static br.com.alura.produto.domain.entity.factory.ProductoFactory.crearProducto;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = JpaConfig.class)
class ProductoRepositoryExtTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    ProductoRepository productoRepository;

    @DisplayName("Cuando guardar un producto")
    @Nested
    class Save {

        @DisplayName("Entonces debe ejecutar con éxito")
        @Nested
        class Success {

            @DisplayName("Dado un producto con todos los campos")
            @Test
            void test1() {
                // Given
                var producto = crearProducto()
                        .conTodosLosCamposExceptoDB();
                // When
                var actual = productoRepository.save(producto);
                // Then
                assertThat(actual.getProductoId())
                        .isNotNull();
            }
        }
    }
}