package br.com.desafio.infrastructure.dataprovider.client;

import br.com.desafio.infrastructure.dataprovider.client.dto.clientes.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "clienteFeignClient", url = "${feign.client.url.cliente}")
public interface ClienteFeignClient {

    @GetMapping("/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json")
    List<ClienteDTO> getClientes();
}