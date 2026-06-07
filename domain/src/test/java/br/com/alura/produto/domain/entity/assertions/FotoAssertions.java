package br.com.alura.produto.domain.entity.assertions;

import br.com.alura.produto.domain.entity.Foto;
import lombok.RequiredArgsConstructor;

import static br.com.alura.produto.domain.util.ReflectionUtil.afirmaQueElObjeto;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RequiredArgsConstructor(access = PRIVATE)
public final class FotoAssertions {

    private final Foto actual;

    public static FotoAssertions afirmaQue_Foto(Foto actual) {
        return new FotoAssertions(spy(actual));
    }

    public void fueConvertidoDe_ProductoDto_Request() {
        assertThat(actual.getFotoId())
                .isNull();
        assertThat(actual.getFileName())
                .isEqualTo("file-name-1.jpg");
        assertThat(actual.getLink())
                .isNull();
        assertThat(actual.getBase64())
                .isEqualTo("Y29udGVudC0x");
        assertThat(actual.getCreadoEn())
                .isNull();
        assertThat(actual.getActualizadoEn())
                .isNull();
        // E
        afirmaQueElObjeto(actual)
                .tuvoTodosLosMetodosGetVerificadosAlMenosUnaVez();
    }
}