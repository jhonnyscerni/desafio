package br.com.desafio.domain.gateway;

import br.com.desafio.domain.model.Compra;
import br.com.desafio.domain.model.Recomendacao;

import java.util.List;

public interface CompraGateway {

    List<Compra> listarComprasOrdenadas();

    List<Compra> obterMaiorCompraPorAno(int ano);

    List<Recomendacao> obterRecomendacaoVinho(String cpf);
}
