package br.com.desafio.infrastructure.scheduler;

import br.com.desafio.infrastructure.dataprovider.client.ClienteClient;
import br.com.desafio.infrastructure.dataprovider.client.ProdutoClient;
import br.com.desafio.infrastructure.dataprovider.client.dto.clientes.ClienteDTO;
import br.com.desafio.infrastructure.dataprovider.client.dto.produtos.ProdutoDTO;
import br.com.desafio.infrastructure.dataprovider.mapper.ClienteMapper;
import br.com.desafio.infrastructure.dataprovider.mapper.CompraMapper;
import br.com.desafio.infrastructure.dataprovider.mapper.ProdutoMapper;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ClienteEntity;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.CompraEntity;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ProdutoEntity;
import br.com.desafio.infrastructure.dataprovider.persistence.repository.ClienteRepository;
import br.com.desafio.infrastructure.dataprovider.persistence.repository.CompraRepository;
import br.com.desafio.infrastructure.dataprovider.persistence.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DatabasePopulator {

    private final ClienteClient clienteClient;
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    private final ProdutoClient produtoClient;
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    private final CompraRepository compraRepository;
    private final CompraMapper compraMapper;

    @Scheduled(fixedRate = 86400000) // Executa a cada 24 horas (24 * 60 * 60 * 1000 ms)
    public void populateDatabase() {
        List<ProdutoDTO> produtos = produtoClient.getProdutos();
        List<ProdutoEntity> produtoEntities = produtos.stream()
                .map(produtoMapper::mapToEntity)
                .filter(produto -> !produtoRepository.existsByCodigo(produto.getCodigo()))
                .collect(Collectors.toList());
        produtoRepository.saveAll(produtoEntities);

        List<ClienteDTO> clientes = clienteClient.getClientes();

        clientes.forEach(clienteDTO -> {
            ClienteEntity clienteEntity = clienteMapper.mapToEntity(clienteDTO);
            if (!clienteRepository.existsByCpf(clienteDTO.getCpf())) {
                clienteRepository.save(clienteEntity);
            } else {
                clienteEntity = clienteRepository.findByCpf(clienteDTO.getCpf());
            }
            ClienteEntity finalClienteEntity = clienteEntity;
            clienteDTO.getCompras().forEach(compraDTO -> {
                ProdutoEntity produto = produtoRepository.findByCodigo(Long.valueOf(compraDTO.getCodigo()));
                boolean compraExiste = compraRepository.existsByClienteCpfAndProdutoCodigo(clienteDTO.getCpf(), produto.getCodigo());
                if (!compraExiste) {
                    CompraEntity compra = new CompraEntity();
                    compra.setCliente(finalClienteEntity);
                    compra.setProduto(produto);
                    compra.setQuantidade(compraDTO.getQuantidade());
                    compraRepository.save(compra);
                }
            });
        });
    }
}