package br.com.alura.produto.domain.entity.factory;

import br.com.alura.produto.domain.entity.Foto;
import lombok.RequiredArgsConstructor;

import static br.com.alura.produto.domain.util.DateUtil.newDateTime;
import static br.com.alura.produto.domain.util.ReflectionUtil.afirmaQueElObjeto;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class FotoFactory {

    private final Foto.FotoBuilder builder;

    public static FotoFactory crearFoto() {
        return new FotoFactory(Foto.builder());
    }

    public Foto conTodosLosCampos() {
        var result = builder
                .fotoId(1L)
                .fileName("file-name-1.jpg")
                .link("https://example.com/foto1.jpg")
                .base64("Y29udGVudC0x")
                .creadoEn(newDateTime("13/12/2025 23:59:59"))
                .actualizadoEn(newDateTime("14/12/2025 23:59:59"))
                .build();
        // E
        afirmaQueElObjeto(result)
                .noTieneCamposVacios();
        return result;
    }

    public Foto conTodosLosCamposExceptoDB() {
        conTodosLosCampos();
        return builder
                .fotoId(null)
                .creadoEn(null)
                .actualizadoEn(null)
                .build();
    }
}