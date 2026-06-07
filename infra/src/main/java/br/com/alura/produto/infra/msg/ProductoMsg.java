package br.com.alura.produto.infra.msg;

import br.com.alura.produto.domain.entity.Producto;
import br.com.alura.produto.infra.def.ProductoDef;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ProductoMsg implements ProductoDef.Response {

    private UUID productoId;
    private String nombre;
    private String categoria;
    private Producto.Status status;
    private String descripcion;
    private BigDecimal valor;
    @Singular(value = "foto", ignoreNullCollections = true)
    private List<FotoMsg> fotos;
    @Singular(value = "tag", ignoreNullCollections = true)
    private List<String> tags;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
}
