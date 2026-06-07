package br.com.alura.produto.infra.repository;

import br.com.alura.produto.domain.entity.Producto;
import br.com.alura.produto.domain.repository.QueueRepository;
import br.com.alura.produto.infra.mapper.ProductoMsgMapper;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import static org.mapstruct.factory.Mappers.getMapper;

@RequiredArgsConstructor
@Repository
@Primary
@Observed
public class QueueRepositoryImpl implements QueueRepository {

    private final ProductoMsgMapper mapper = getMapper(ProductoMsgMapper.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${message.market-place.queue-exchange}")
    public String queueExchange;

    @Value("${message.registro-producto.routing-key}")
    public String routingKeyName;

    @Override
    public void notificarRegistro(Producto producto) {
        var msg = mapper.converter(producto);
        rabbitTemplate.convertAndSend(queueExchange, routingKeyName, msg);
    }
}
