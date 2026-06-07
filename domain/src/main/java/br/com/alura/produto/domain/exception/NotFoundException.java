package br.com.alura.produto.domain.exception;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> clazz) {
        super(format("Recurso %s no encontrado", clazz.getSimpleName()));
    }
}
