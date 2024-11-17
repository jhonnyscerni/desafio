package br.com.desafio.infrastructure.dataprovider.mapper;


import br.com.desafio.domain.model.Cliente;
import br.com.desafio.infrastructure.dataprovider.client.dto.clientes.ClienteDTO;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteMapper {

    private final CompraMapper compraMapper;

    public ClienteEntity mapToEntity(ClienteDTO clienteDTO) {
        return ClienteEntity.builder()
                .nome(clienteDTO.getNome())
                .cpf(clienteDTO.getCpf())
                .compras(compraMapper.mapToEntityList(clienteDTO.getCompras()))
                .build();
    }

    public Cliente toDomain(ClienteEntity entity) {
        return Cliente.builder()
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .build();
    }
}