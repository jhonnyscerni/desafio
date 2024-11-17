package br.com.desafio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recomendacao {
    private String tipoVinho;
    private String safra;
    private double preco;
    private Long quantidadeTotal;
}