package br.com.desafio.application.usecase;

import br.com.desafio.application.dto.CompraResponseDTO;

public interface ObterMaiorCompraPorAnoUseCase {
    CompraResponseDTO obterMaiorCompraPorAno(int ano);
}
