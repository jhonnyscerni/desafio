package br.com.desafio.application.mapper;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.CompraEntity;

public class ClienteModelMapper {

    public static CompraResponseDTO fromClienteToCompraResponseDTO(CompraEntity compra) {
        return CompraResponseDTO.builder()
                .clienteNome(compra.getCliente().getNome())
                .clienteCpf(compra.getCliente().getCpf())
                .produtoCodigo(compra.getProduto().getCodigo())
                .produtoTipoVinho(compra.getProduto().getTipoVinho())
                .produtoPreco(compra.getProduto().getPreco())
                .produtoSafra(compra.getProduto().getSafra())
                .produtoAnoCompra(compra.getProduto().getAnoCompra())
                .quantidade(compra.getQuantidade())
                .valorTotal(compra.getProduto().getPreco() * compra.getQuantidade())
                .build();
    }
}