package br.com.desafio.infrastructure.entrypoint.api.controller.impl;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;
import br.com.desafio.application.usecase.ObterRecomendacaoVinhoUseCase;
import br.com.desafio.infrastructure.entrypoint.api.controller.RecomendacaoController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recomendacao/cliente")
@RequiredArgsConstructor
public class RecomendacaoControllerImpl implements RecomendacaoController {

    private final ObterRecomendacaoVinhoUseCase obterRecomendacaoVinhoUseCase;

    @GetMapping("/{cpf}/tipo")
    public ResponseEntity<RecomendacaoResponseDTO> getRecomendacaoVinho(@PathVariable String cpf) {
        RecomendacaoResponseDTO recomendacao = obterRecomendacaoVinhoUseCase.execute(cpf);
        return ResponseEntity.ok(recomendacao);
    }
}
