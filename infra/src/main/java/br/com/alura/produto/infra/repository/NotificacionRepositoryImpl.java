package br.com.alura.produto.infra.repository;

import br.com.alura.produto.domain.entity.Producto;
import br.com.alura.produto.domain.repository.NotificacionRepository;
import io.micrometer.observation.annotation.Observed;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@Observed
public class NotificacionRepositoryImpl implements NotificacionRepository {

    @Override
    public void notificar(Producto producto) {

    }
}
