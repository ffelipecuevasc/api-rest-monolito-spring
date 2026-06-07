package br.com.alura.produto.infra.def;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface FotoDef {

    interface Representado extends Serializable {
        String getFileName();
    }

    interface Detallado extends Serializable, Representado {

    }

    interface RepresentadoPersistido extends Serializable, Representado {
        Long getFotoId();

        String getLink();

        LocalDateTime getCreadoEn();

        LocalDateTime getActualizadoEn();
    }

    interface DetalladoPersistido extends Serializable, RepresentadoPersistido {

    }

    interface Request extends Detallado {

        String getBase64();
    }

    interface Response extends Detallado, DetalladoPersistido {

    }

    interface Representacion extends RepresentadoPersistido {

    }
}
