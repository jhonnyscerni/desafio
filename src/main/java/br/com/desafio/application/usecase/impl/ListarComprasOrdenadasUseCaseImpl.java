package br.com.desafio.application.usecase.impl;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.application.mapper.CompraModelMapper;
import br.com.desafio.application.usecase.ListarComprasOrdenadasUseCase;
import br.com.desafio.domain.gateway.CompraGateway;
import br.com.desafio.infrastructure.dataprovider.mapper.CompraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListarComprasOrdenadasUseCaseImpl implements ListarComprasOrdenadasUseCase {

    private final CompraGateway compraGateway;

    @Override
    public List<CompraResponseDTO> listarComprasOrdenadas() {
        return compraGateway.listarComprasOrdenadas().stream()
                .map(CompraModelMapper::toDomain)
                .collect(Collectors.toList());
    }
}
