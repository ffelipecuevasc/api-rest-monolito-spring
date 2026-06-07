package br.com.alura.produto.domain.usecase;

import br.com.alura.produto.domain.entity.Producto;
import br.com.alura.produto.domain.exception.NotFoundException;
import br.com.alura.produto.domain.repository.ProductoRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Observed
public class ConsultaProductoUseCase {

    private final ProductoRepository productoRepository;

    public Producto consultarPorId(UUID id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Producto.class));
    }
}
