package br.com.alura.produto.application.v1.dto;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static br.com.alura.produto.application.v1.dto.FotoDtoFactory.crearFotoDtoRequest;
import static br.com.alura.produto.domain.entity.Producto.Status.AVAILABLE;
import static br.com.alura.produto.domain.util.ReflectionUtil.afirmaQueElObjeto;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ProductoDtoFactory {

    public static Request crearProductoDtoRequest() {
        return new Request(ProductoDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Request {

        private final ProductoDto.Request.RequestBuilder builder;

        public ProductoDto.Request conTodosLosCampos() {
            var result = builder
                    .nombre("Producto Prueba")
                    .categoria("Categoría 1")
                    .status(AVAILABLE)
                    .descripcion("Descripción del Producto Prueba")
                    .valor(new BigDecimal("1.99"))
                    .foto(crearFotoDtoRequest()
                            .conTodosLosCampos())
                    .tag("tag-1")
                    .build();
            afirmaQueElObjeto(result)
                    .noTieneCamposVacios();
            return result;
        }
    }
}