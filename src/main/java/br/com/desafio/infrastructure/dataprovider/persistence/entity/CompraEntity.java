package br.com.desafio.infrastructure.dataprovider.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "compra")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    private int quantidade;
}