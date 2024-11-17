package br.com.desafio.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompraResponseDTO {
    private String clienteNome;
    private String clienteCpf;
    private Long produtoCodigo;
    private String produtoTipoVinho;
    private double produtoPreco;
    private String produtoSafra;
    private int produtoAnoCompra;
    private int quantidade;
    private double valorTotal;
}