package br.com.desafio.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private Long codigo;
    @JsonProperty("tipo_vinho")
    private String tipoVinho;
    private double preco;
    private String safra;
    @JsonProperty("ano_compra")
    private int anoCompra;
}
