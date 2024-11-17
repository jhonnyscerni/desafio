package br.com.desafio.infrastructure.dataprovider.client;

import br.com.desafio.infrastructure.dataprovider.client.dto.produtos.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "produtoFeignClient", url = "${feign.client.url.produto}")
public interface ProdutoClient {

    @GetMapping("/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json")
    List<ProdutoDTO> getProdutos();
}