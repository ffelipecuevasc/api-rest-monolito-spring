package br.com.alura.produto.domain.usecase;

import br.com.alura.produto.domain.entity.Producto;
import br.com.alura.produto.domain.repository.BucketRepository;
import br.com.alura.produto.domain.repository.ProductoRepository;
import br.com.alura.produto.domain.repository.QueueRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.alura.produto.domain.util.ValidationUtil.validate;

@RequiredArgsConstructor
@Service
@Observed
public class RegistroProductoUseCase {

    private final ProductoRepository productoRepository;

    private final BucketRepository bucketRepository;

    private final QueueRepository queueRepository;

    public Producto registrar(Producto producto) {
        validate(producto);

        if (!producto.getFotos().isEmpty())
            producto.getFotos()
                    .forEach(bucketRepository::almacenar);

        var productoGuardado = productoRepository.save(producto);

        queueRepository.notificarRegistro(productoGuardado);

        return productoGuardado;
    }
}
