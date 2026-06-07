package br.com.alura.produto.domain.repository;

import br.com.alura.produto.domain.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductoRepository extends CrudRepository<Producto, UUID> {

}
