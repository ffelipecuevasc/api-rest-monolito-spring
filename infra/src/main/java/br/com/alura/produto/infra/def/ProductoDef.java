package br.com.alura.produto.infra.def;

import br.com.alura.produto.domain.entity.Producto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProductoDef {

    interface Representado extends Serializable {

        String getNombre();

        String getCategoria();

        Producto.Status getStatus();

        BigDecimal getValor();
    }

    interface Detallado extends Serializable, Representado {

        String getDescripcion();

        List<String> getTags();
    }

    interface RepresentadoPersistido extends Serializable, Representado {

        UUID getProductoId();

        LocalDateTime getCreadoEn();

        LocalDateTime getActualizadoEn();
    }

    interface DetalladoPersistido extends Serializable, RepresentadoPersistido {

    }

    interface Request extends Detallado {

        List<? extends FotoDef.Request> getFotos();
    }

    interface Response extends Detallado, DetalladoPersistido {

        List<? extends FotoDef.Response> getFotos();
    }

    interface Representacion extends RepresentadoPersistido {

    }
}
