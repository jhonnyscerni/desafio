package br.com.desafio.infrastructure.dataprovider.gateway.impl;

import br.com.desafio.domain.gateway.CompraGateway;
import br.com.desafio.domain.model.Compra;
import br.com.desafio.domain.model.Recomendacao;
import br.com.desafio.infrastructure.dataprovider.mapper.CompraMapper;
import br.com.desafio.infrastructure.dataprovider.persistence.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompraGatewayImpl implements CompraGateway {

    private final CompraRepository compraRepository;
    private final CompraMapper compraMapper;

    @Override
    public List<Compra> listarComprasOrdenadas() {
        return compraRepository.findAllOrderByTotalValueAsc().stream()
                .map(compraMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Compra> obterMaiorCompraPorAno(int ano) {
        return compraRepository.findMaiorCompraPorAno(ano).stream()
                .map(compraMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recomendacao> obterRecomendacaoVinho(String cpf) {
        return compraRepository.findTopWineTypeByCliente(cpf);
    }
}