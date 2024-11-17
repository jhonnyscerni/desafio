package br.com.desafio.application.usecase;

import br.com.desafio.application.dto.ClienteFielResponseDTO;

import java.util.List;

public interface ObterClientesFieisUseCase {

    List<ClienteFielResponseDTO> execute();
}
