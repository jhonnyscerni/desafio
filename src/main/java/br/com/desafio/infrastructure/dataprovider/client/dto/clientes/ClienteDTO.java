package br.com.desafio.infrastructure.dataprovider.client.dto.clientescompras;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private String nome;
    private String cpf;
    private List<CompraDTO> compras;
}