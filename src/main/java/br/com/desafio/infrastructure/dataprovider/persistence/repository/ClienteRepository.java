package br.com.desafio.infrastructure.dataprovider.persistence.repository;

import br.com.desafio.infrastructure.dataprovider.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c JOIN c.compras co GROUP BY c ORDER BY SUM(co.produto.preco * co.quantidade) DESC, COUNT(co) DESC")
    List<ClienteEntity> findTop3ClientesFieis();

    boolean existsByCpf(String cpf);

    @Query("SELECT c FROM ClienteEntity c WHERE c.cpf = :cpf")
    ClienteEntity findByCpf(@Param("cpf") String cpf);
}
