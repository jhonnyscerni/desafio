package br.com.desafio.infrastructure.dataprovider.persistence.repository;

import br.com.desafio.domain.model.Recomendacao;
import br.com.desafio.infrastructure.dataprovider.persistence.dto.TipoVinhoQuantidadeDTO;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ClienteEntity;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.CompraEntity;
import br.com.desafio.infrastructure.dataprovider.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompraRepository extends JpaRepository<CompraEntity, Long> {

    @Query("SELECT COUNT(c) > 0 FROM CompraEntity c WHERE c.cliente.cpf = :cpf AND c.produto.codigo = :codigo")
    boolean existsByClienteCpfAndProdutoCodigo(@Param("cpf") String cpf, @Param("codigo") Long codigo);

    @Query("SELECT c FROM CompraEntity c ORDER BY (c.produto.preco * c.quantidade) ASC")
    List<CompraEntity> findAllOrderByTotalValueAsc();

    @Query("SELECT c FROM CompraEntity c WHERE YEAR(c.produto.anoCompra) = :ano ORDER BY (c.produto.preco * c.quantidade) DESC")
    List<CompraEntity> findMaiorCompraPorAno(int ano);

    @Query("SELECT new  br.com.desafio.domain.model.Recomendacao(c.produto.tipoVinho, c.produto.safra, c.produto.preco, SUM(c.quantidade)) " +
            "FROM CompraEntity c WHERE c.cliente.cpf = :cpf " +
            "GROUP BY c.produto.tipoVinho, c.produto.safra, c.produto.preco " +
            "ORDER BY SUM(c.quantidade) DESC")
    List<Recomendacao> findTopWineTypeByCliente(@Param("cpf") String cpf);

}