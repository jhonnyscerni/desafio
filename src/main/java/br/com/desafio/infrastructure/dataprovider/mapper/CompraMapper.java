package br.com.desafio.infrastructure.dataprovider.mapper;

import br.com.desafio.domain.model.Cliente;
import br.com.desafio.domain.model.Compra;
import br.com.desafio.domain.model.Produto;
import br.com.desafio.infrastructure.dataprovider.client.dto.compras.CompraDTO;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ClienteEntity;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.CompraEntity;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ProdutoEntity;
import br.com.desafio.infrastructure.dataprovider.persistence.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompraMapper {

    private final ProdutoRepository produtoRepository;

    public CompraEntity mapToEntity(CompraDTO compraDTO) {
        ProdutoEntity produto = produtoRepository.findByCodigo(Long.valueOf(compraDTO.getCodigo()));
        return CompraEntity.builder()
                .produto(produto)
                .quantidade(compraDTO.getQuantidade())
                .build();
    }

    public Compra toDomain(CompraEntity entity) {
        return Compra.builder()
                .id(entity.getId())
                .produto(mapProdutoEntityToProduto(entity.getProduto()))
                .cliente(mapClienteEntityToCliente(entity.getCliente()))
                .quantidade(entity.getQuantidade())
                .build();
    }

    private Produto mapProdutoEntityToProduto(ProdutoEntity produtoEntity) {
        return Produto.builder()
                .codigo(produtoEntity.getCodigo())
                .tipoVinho(produtoEntity.getTipoVinho())
                .preco(produtoEntity.getPreco())
                .safra(produtoEntity.getSafra())
                .anoCompra(produtoEntity.getAnoCompra())
                .build();
    }

    private Cliente mapClienteEntityToCliente(ClienteEntity clienteEntity) {
        return Cliente.builder()
                .nome(clienteEntity.getNome())
                .cpf(clienteEntity.getCpf())
                .build();
    }

    public List<CompraEntity> mapToEntityList(List<CompraDTO> compraDTOs) {
        return compraDTOs.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }
}