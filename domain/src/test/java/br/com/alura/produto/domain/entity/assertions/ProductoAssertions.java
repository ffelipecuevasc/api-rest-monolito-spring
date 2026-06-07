package br.com.alura.produto.domain.entity.assertions;

import br.com.alura.produto.domain.entity.Producto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.alura.produto.domain.entity.Producto.Status.AVAILABLE;
import static br.com.alura.produto.domain.entity.assertions.FotoAssertions.afirmaQue_Foto;
import static br.com.alura.produto.domain.util.ReflectionUtil.afirmaQueElObjeto;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class ProductoAssertions {

    private final Producto actual;

    public static ProductoAssertions afirmaQue_Producto(Producto actual) {
        return new ProductoAssertions(spy(actual));
    }

    public void fueConvertidoDe_ProductoDto_Request() {
        assertThat(actual.getProductoId())
                .isNull();
        assertThat(actual.getNombre())
                .isEqualTo("Producto Prueba");
        assertThat(actual.getCategoria())
                .isEqualTo("Categoría 1");
        assertThat(actual.getStatus())
                .isEqualTo(AVAILABLE);
        assertThat(actual.getDescripcion())
                .isEqualTo("Descripción del Producto Prueba");
        assertThat(actual.getValor())
                .isEqualTo(new BigDecimal("1.99"));
        afirmaQue_Foto(actual.getFotos().getFirst())
                .fueConvertidoDe_ProductoDto_Request();
        assertThat(actual.getTags().getFirst())
                .isEqualTo("tag-1");
        assertThat(actual.getCreadoEn())
                .isNull();
        assertThat(actual.getActualizadoEn())
                .isNull();
        afirmaQueElObjeto(actual)
                .tuvoTodosLosMetodosGetVerificadosAlMenosUnaVez();
    }
}