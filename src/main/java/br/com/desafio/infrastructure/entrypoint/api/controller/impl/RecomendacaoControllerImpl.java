package br.com.desafio.infrastructure.entrypoint.api.controller.impl;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;
import br.com.desafio.application.usecase.ObterRecomendacaoVinhoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recomendacao/cliente")
@RequiredArgsConstructor
public class RecomendacaoControllerImpl {

    private final ObterRecomendacaoVinhoUseCase obterRecomendacaoVinhoUseCase;

    @GetMapping("/{clienteId}/tipo")
    public RecomendacaoResponseDTO getRecomendacaoVinho(@PathVariable Long clienteId) {
        return obterRecomendacaoVinhoUseCase.execute(clienteId);
    }
}
