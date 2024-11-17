package br.com.desafio.application.mapper;

import br.com.desafio.application.dto.ClienteFielResponseDTO;
import br.com.desafio.domain.model.Cliente;

public class ClienteModelMapper {

    public static ClienteFielResponseDTO toDomain(Cliente cliente) {
        return ClienteFielResponseDTO.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .build();
    }
}