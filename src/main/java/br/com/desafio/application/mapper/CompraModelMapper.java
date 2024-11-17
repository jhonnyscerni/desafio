package br.com.desafio.application.mapper;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.domain.model.Compra;

public class CompraModelMapper {

    public static CompraResponseDTO toDomain(Compra compra) {
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