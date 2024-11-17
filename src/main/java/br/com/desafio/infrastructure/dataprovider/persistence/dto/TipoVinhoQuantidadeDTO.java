package br.com.desafio.infrastructure.dataprovider.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoVinhoQuantidadeDTO {

    private String tipoVinho;
    private String safra;
    private double preco;
    private Long quantidadeTotal;
}