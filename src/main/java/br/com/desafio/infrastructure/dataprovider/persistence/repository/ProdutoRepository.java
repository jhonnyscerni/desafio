package br.com.desafio.infrastructure.dataprovider.persistence.repository;

import br.com.desafio.infrastructure.dataprovider.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    boolean existsByCodigo(Long codigo);
    ProdutoEntity findByCodigo(Long codigo);
}