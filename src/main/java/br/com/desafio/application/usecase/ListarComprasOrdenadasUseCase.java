package br.com.desafio.application.usecase;

import br.com.desafio.application.dto.CompraResponseDTO;

import java.util.List;

public interface ListarComprasOrdenadasUseCase {

    List<CompraResponseDTO> listarComprasOrdenadas();
}
