package br.com.desafio.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecomendacaoResponseDTO {
    private String tipoVinho;
    private String safra;
    private double preco;
    private Long quantidadeTotal;
}