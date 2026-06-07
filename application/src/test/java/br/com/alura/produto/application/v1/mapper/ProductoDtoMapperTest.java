package br.com.alura.produto.application.v1.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static br.com.alura.produto.application.v1.dto.ProductoDtoFactory.crearProductoDtoRequest;
import static br.com.alura.produto.application.v1.dto.assertions.ProductoDtoAssertions.afirmaQue_ProdutoDto_Response;
import static br.com.alura.produto.domain.entity.assertions.ProductoAssertions.afirmaQue_Producto;
import static br.com.alura.produto.domain.entity.factory.ProductoFactory.crearProducto;
import static org.mapstruct.factory.Mappers.getMapper;

class ProductoDtoMapperTest {

    private static final ProductoDtoMapper mapper = getMapper(ProductoDtoMapper.class);

    @DisplayName("Cuando convertir ProductoDto.Request a Producto")
    @Nested
    class Convertir1 {

        @DisplayName("Entonces debe ejecutar con éxito")
        @Nested
        class Exito {

            @DisplayName("Dado un ProductoDto.Request con todos los campos")
            @Test
            void prueba1() {
                // Given
                var source = crearProductoDtoRequest()
                        .conTodosLosCampos();
                // When
                var actual = mapper.convertir(source);
                // Then
                afirmaQue_Producto(actual)
                        .fueConvertidoDe_ProductoDto_Request();
            }
        }
    }

    @DisplayName("Cuando convertir Producto a ProductoDto.Response")
    @Nested
    class Convertir2 {

        @DisplayName("Entonces debe ejecutar con éxito")
        @Nested
        class Exito {

            @DisplayName("Dado un Producto con todos los campos")
            @Test
            void prueba1() {
                // Given
                var source = crearProducto()
                        .conTodosLosCampos();
                // When
                var actual = mapper.convertir(source);
                // Then
                afirmaQue_ProdutoDto_Response(actual)
                        .fueConvertidoDe_Producto();
            }
        }
    }
}