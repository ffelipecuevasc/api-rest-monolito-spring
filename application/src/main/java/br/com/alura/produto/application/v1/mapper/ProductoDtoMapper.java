package br.com.alura.produto.application.v1.mapper;

import br.com.alura.produto.application.v1.dto.ProductoDto;
import br.com.alura.produto.domain.entity.Producto;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(nullValuePropertyMappingStrategy = IGNORE)
public interface ProductoDtoMapper {

    Producto convertir(ProductoDto.Request source);

    ProductoDto.Response convertir(Producto source);

    ProductoDto.Representacion convertirParaRepresentacion(Producto source);
}
