package br.com.desafio.domain.gateway;

import br.com.desafio.domain.model.Cliente;

import java.util.List;

public interface ClienteGateway {

    List<Cliente> obterTop3ClientesFieis();
}
