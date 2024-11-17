package br.com.desafio.infrastructure.dataprovider.client.dto.compras;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  CompraDTO {
    private String codigo;
    private int quantidade;
}
