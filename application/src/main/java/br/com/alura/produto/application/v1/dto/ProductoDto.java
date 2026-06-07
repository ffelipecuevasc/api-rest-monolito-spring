package br.com.alura.produto.application.v1.dto;

import br.com.alura.produto.domain.entity.Producto;
import br.com.alura.produto.infra.def.ProductoDef;
import jakarta.validation.constraints.DecimalMin;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ProductoDto {

    @Getter
    @Builder
    public static class Request implements ProductoDef.Request {

        private String nombre;
        private String categoria;
        private Producto.Status status;
        private String descripcion;
        @DecimalMin(value = "1.99", message = "debe ser mayor o igual a $ 1.99")
        private BigDecimal valor;
        @Singular(value = "foto", ignoreNullCollections = true)
        private List<FotoDto.Request> fotos;
        @Singular(value = "tag", ignoreNullCollections = true)
        private List<String> tags;
    }

    @Getter
    @Builder
    public static class Response implements ProductoDef.Response {

        private UUID productoId;
        private String nombre;
        private String categoria;
        private Producto.Status status;
        private String descripcion;
        private BigDecimal valor;
        @Singular(value = "foto", ignoreNullCollections = true)
        private List<FotoDto.Response> fotos;
        @Singular(value = "tag", ignoreNullCollections = true)
        private List<String> tags;
        private LocalDateTime creadoEn;
        private LocalDateTime actualizadoEn;
    }

    @Getter
    @Builder
    public static class Representacion implements ProductoDef.Representacion {

        private UUID productoId;
        private String nombre;
        private String categoria;
        private Producto.Status status;
        private BigDecimal valor;
        private LocalDateTime creadoEn;
        private LocalDateTime actualizadoEn;
    }
}
