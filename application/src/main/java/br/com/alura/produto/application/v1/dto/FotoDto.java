package br.com.alura.produto.application.v1.dto;

import br.com.alura.produto.infra.def.FotoDef;
import br.com.alura.produto.application.v1.mapper.ProductoDtoMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;
import static org.mapstruct.factory.Mappers.getMapper;

@NoArgsConstructor(access = PRIVATE)
public final class FotoDto {

    public static final ProductoDtoMapper mapper = getMapper(ProductoDtoMapper.class);

    @Getter
    @Builder
    public static class Request implements FotoDef.Request {

        private String fileName;
        private String base64;
    }

    @Getter
    @Builder
    public static class Response implements FotoDef.Response {

        private Long fotoId;
        private String fileName;
        private String link;
        private LocalDateTime creadoEn;
        private LocalDateTime actualizadoEn;
    }

    @Getter
    @Builder
    public static class Representacion implements FotoDef.Representacion {

        private Long fotoId;
        private String fileName;
        private String link;
        private LocalDateTime creadoEn;
        private LocalDateTime actualizadoEn;
    }
}
