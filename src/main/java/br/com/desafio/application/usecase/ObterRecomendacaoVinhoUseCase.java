package br.com.desafio.application.usecase;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;

public interface ObterRecomendacaoVinhoUseCase {

    RecomendacaoResponseDTO execute(String cpf);
}
