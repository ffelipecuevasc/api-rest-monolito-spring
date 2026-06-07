package br.com.alura.produto.infra.repository;

import br.com.alura.produto.domain.repository.ProductoRepository;
import io.micrometer.observation.annotation.Observed;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@Observed
public interface ProductoRepositoryExt extends ProductoRepository {

}
