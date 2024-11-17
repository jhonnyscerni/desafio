package br.com.desafio.infrastructure.dataprovider.gateway.impl;

import br.com.desafio.infrastructure.dataprovider.client.ProdutoClient;
import br.com.desafio.infrastructure.dataprovider.client.dto.produtos.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProdutoGatewayImpl {

    private final ProdutoClient produtoClient;

    public List<ProdutoDTO> getProdutos() {
        return produtoClient.getProdutos();
    }
}
