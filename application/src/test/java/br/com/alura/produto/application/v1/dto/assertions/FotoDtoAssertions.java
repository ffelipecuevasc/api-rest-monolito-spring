package br.com.alura.produto.application.v1.dto.assertions;

import br.com.alura.produto.application.v1.dto.FotoDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static br.com.alura.produto.domain.util.DateUtil.newDateTime;
import static br.com.alura.produto.domain.util.ReflectionUtil.afirmaQueElObjeto;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@NoArgsConstructor(access = PRIVATE)
public final class FotoDtoAssertions {

    public static Response afirmaQue_FotoDto_Response(FotoDto.Response actual) {
        return new Response(spy(actual));
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static class Response {

        private final FotoDto.Response actual;

        public void fueConvertidoDe_Foto() {
            assertThat(actual.getFotoId())
                    .isEqualTo(1L);
            assertThat(actual.getFileName())
                    .isEqualTo("file-name-1.jpg");
            assertThat(actual.getLink())
                    .isEqualTo("https://example.com/foto1.jpg");
            assertThat(actual.getCreadoEn())
                    .isEqualTo(newDateTime("13/12/2025 23:59:59"));
            assertThat(actual.getActualizadoEn())
                    .isEqualTo(newDateTime("14/12/2025 23:59:59"));
            afirmaQueElObjeto(actual)
                    .tuvoTodosLosMetodosGetVerificadosAlMenosUnaVez();
        }
    }
}