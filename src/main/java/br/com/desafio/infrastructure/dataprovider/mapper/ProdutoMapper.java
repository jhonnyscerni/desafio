package br.com.desafio.infrastructure.dataprovider.mapper;

import br.com.desafio.infrastructure.dataprovider.client.dto.produtos.ProdutoDTO;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProdutoMapper {

    public ProdutoEntity mapToEntity(ProdutoDTO produtoDTO) {
        return ProdutoEntity.builder()
                .codigo(produtoDTO.getCodigo())
                .tipoVinho(produtoDTO.getTipoVinho())
                .preco(produtoDTO.getPreco())
                .safra(produtoDTO.getSafra())
                .anoCompra(LocalDate.of(produtoDTO.getAnoCompra(), 1, 1))
                .build();
    }
}