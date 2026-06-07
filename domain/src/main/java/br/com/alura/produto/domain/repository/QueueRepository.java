package br.com.alura.produto.domain.repository;

import br.com.alura.produto.domain.entity.Producto;

public interface QueueRepository {

    void notificarRegistro(Producto producto);
}
