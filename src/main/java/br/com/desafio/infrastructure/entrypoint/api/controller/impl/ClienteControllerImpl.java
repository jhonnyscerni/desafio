package br.com.desafio.infrastructure.entrypoint.api.controller.impl;

import br.com.desafio.application.dto.ClienteFielResponseDTO;
import br.com.desafio.application.usecase.ObterClientesFieisUseCase;
import br.com.desafio.infrastructure.entrypoint.api.controller.ClienteController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClienteControllerImpl implements ClienteController {

    private final ObterClientesFieisUseCase obterClientesFieisUseCase;

    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<ClienteFielResponseDTO>> getClientesFieis() {
        List<ClienteFielResponseDTO> clientesFieis = obterClientesFieisUseCase.execute();
        return ResponseEntity.ok(clientesFieis);
    }
}
