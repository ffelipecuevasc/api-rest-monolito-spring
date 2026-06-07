package br.com.alura.produto.application.v1.dto.assertions;

import br.com.alura.produto.application.v1.dto.ProductoDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.alura.produto.application.v1.dto.assertions.FotoDtoAssertions.afirmaQue_FotoDto_Response;
import static br.com.alura.produto.domain.entity.Producto.Status.AVAILABLE;
import static br.com.alura.produto.domain.util.DateUtil.newDateTime;
import static br.com.alura.produto.domain.util.ReflectionUtil.afirmaQueElObjeto;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@NoArgsConstructor(access = PRIVATE)
public final class ProductoDtoAssertions {

    public static Response afirmaQue_ProdutoDto_Response(ProductoDto.Response actual) {
        return new Response(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static class Response {

        private final ProductoDto.Response actual;

        public void fueConvertidoDe_Producto() {
            assertThat(actual.getProductoId())
                    .hasToString("45bd68cf-f261-4187-ad59-8e8f9cce47e6");
            assertThat(actual.getNombre())
                    .isEqualTo("Producto 1");
            assertThat(actual.getCategoria())
                    .isEqualTo("Categoría 1");
            assertThat(actual.getStatus())
                    .isEqualTo(AVAILABLE);
            assertThat(actual.getDescripcion())
                    .isEqualTo("Descripción 1");
            assertThat(actual.getValor())
                    .isEqualTo(new BigDecimal("1.99"));
            afirmaQue_FotoDto_Response(actual.getFotos().getFirst())
                    .fueConvertidoDe_Foto();
            assertThat(actual.getTags().getFirst())
                    .isEqualTo("Tag 1");
            assertThat(actual.getCreadoEn())
                    .isEqualTo(newDateTime("21/12/2025 23:59:59"));
            assertThat(actual.getActualizadoEn())
                    .isEqualTo(newDateTime("22/12/2025 23:59:59"));
            afirmaQueElObjeto(actual)
                    .tuvoTodosLosMetodosGetVerificadosAlMenosUnaVez();
        }
    }
}