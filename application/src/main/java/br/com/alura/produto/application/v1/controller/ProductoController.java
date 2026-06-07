package br.com.alura.produto.application.v1.controller;

import br.com.alura.produto.application.v1.dto.ProductoDto;
import br.com.alura.produto.application.v1.mapper.ProductoDtoMapper;
import br.com.alura.produto.domain.usecase.RegistroProductoUseCase;
import br.com.alura.produto.domain.usecase.ConsultaProductoUseCase;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.mapstruct.factory.Mappers.getMapper;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/productos")
@Observed
public class ProductoController {

    public static final ProductoDtoMapper mapper = getMapper(ProductoDtoMapper.class);

    private final RegistroProductoUseCase cadastroCarrinhoUseCase;

    private final ConsultaProductoUseCase consultaProductoUseCase;

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ProductoDto.Response registrarProducto(
            @Valid
            @RequestBody
            ProductoDto.Request requestBody) {
        var producto = mapper.convertir(requestBody);
        var productoCreado = cadastroCarrinhoUseCase.registrar(producto);
        return mapper.convertir(productoCreado);
    }

    @GetMapping(path = "/{productoId}",
            produces = APPLICATION_JSON_VALUE)
    @Cacheable(value = "Productos", key = "#productoId")
    public ProductoDto.Response consultarProdutoPorId(
            @PathVariable("productoId")
            UUID productoId) {
        var productoCargado = consultaProductoUseCase.consultarPorId(productoId);
        return mapper.convertir(productoCargado);
    }
}
