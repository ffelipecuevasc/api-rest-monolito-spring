package br.com.alura.produto.domain.repository;

import br.com.alura.produto.domain.entity.Producto;

public interface NotificacionRepository {

    void notificar(Producto producto);
}
