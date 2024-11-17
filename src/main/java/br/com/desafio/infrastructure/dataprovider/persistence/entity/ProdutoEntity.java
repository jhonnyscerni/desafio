package br.com.desafio.infrastructure.dataprovider.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "produto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    private Long codigo;

    private String tipoVinho;

    private double preco;

    private String safra;


    @Column(name = "ano_compra")
    private LocalDate anoCompra;

    public int getAnoCompra() {
        return anoCompra.getYear();
    }
}