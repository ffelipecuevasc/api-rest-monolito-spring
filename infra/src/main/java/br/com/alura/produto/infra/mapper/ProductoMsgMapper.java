package br.com.alura.produto.infra.mapper;

import br.com.alura.produto.domain.entity.Producto;
import br.com.alura.produto.infra.msg.ProductoMsg;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface ProductoMsgMapper {

    ProductoMsg converter(Producto source);
}
