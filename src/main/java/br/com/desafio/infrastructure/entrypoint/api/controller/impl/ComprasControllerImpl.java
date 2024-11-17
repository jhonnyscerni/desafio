package br.com.desafio.infrastructure.entrypoint.api.controller.impl;

import br.com.desafio.application.dto.CompraResponseDTO;
import br.com.desafio.application.usecase.ListarComprasOrdenadasUseCase;
import br.com.desafio.application.usecase.ObterMaiorCompraPorAnoUseCase;
import br.com.desafio.infrastructure.entrypoint.api.controller.ComprasController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ComprasControllerImpl implements ComprasController {

    private final ListarComprasOrdenadasUseCase listarComprasOrdenadasUseCase;
    private final ObterMaiorCompraPorAnoUseCase obterMaiorCompraPorAnoUseCase;

    @GetMapping("/compras")
    public ResponseEntity<List<CompraResponseDTO>> listarComprasOrdenadas() {
        List<CompraResponseDTO> compras = listarComprasOrdenadasUseCase.listarComprasOrdenadas();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<CompraResponseDTO> getMaiorCompraPorAno(@PathVariable int ano) {
        CompraResponseDTO compra = obterMaiorCompraPorAnoUseCase.obterMaiorCompraPorAno(ano);
        return ResponseEntity.ok(compra);
    }
}
