package br.com.alura.produto.domain.exception;

public class ErrorInternoException extends RuntimeException {

    public ErrorInternoException() {
        super("Error interno");
    }
}
