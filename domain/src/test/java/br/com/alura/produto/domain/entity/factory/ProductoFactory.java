package br.com.alura.produto.domain.entity.factory;

import br.com.alura.produto.domain.entity.Producto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.alura.produto.domain.entity.Producto.Status.AVAILABLE;
import static br.com.alura.produto.domain.entity.factory.FotoFactory.crearFoto;
import static br.com.alura.produto.domain.util.DateUtil.newDateTime;
import static br.com.alura.produto.domain.util.ReflectionUtil.afirmaQueElObjeto;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ProductoFactory {

    private final Producto.ProductoBuilder builder;

    public static ProductoFactory crearProducto() {
        return new ProductoFactory(Producto.builder());
    }

    public Producto conTodosLosCampos() {
        var result = builder
                .productoId(UUID.fromString("45bd68cf-f261-4187-ad59-8e8f9cce47e6"))
                .nombre("Producto 1")
                .categoria("Categoría 1")
                .tag("Tag 1")
                .status(AVAILABLE)
                .descripcion("Descripción 1")
                .valor(new BigDecimal("1.99"))
                .foto(crearFoto()
                        .conTodosLosCampos())
                .creadoEn(newDateTime("21/12/2025 23:59:59"))
                .actualizadoEn(newDateTime("22/12/2025 23:59:59"))
                .build();
        // E
        afirmaQueElObjeto(result)
                .noTieneCamposVacios();
        return result;
    }

    public Producto conTodosLosCamposExceptoDB() {
        conTodosLosCampos();
        return builder
                .productoId(null)
                .creadoEn(null)
                .actualizadoEn(null)
                .clearFotos()
                .foto(crearFoto()
                        .conTodosLosCamposExceptoDB())
                .build();
    }
}
