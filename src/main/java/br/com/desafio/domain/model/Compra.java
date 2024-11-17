package br.com.desafio.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Compra {
    private Long id;

    private Produto produto;

    private Cliente cliente;

    private int quantidade;
}