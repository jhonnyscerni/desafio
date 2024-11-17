package br.com.desafio.infrastructure.entrypoint.api.controller;

import br.com.desafio.application.dto.CompraResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Compras Controller", description = "Endpoints para gerenciar compras")
public interface ComprasController {

    @Operation(summary = "Listar compras ordenadas", description = "Retorna uma lista de compras ordenadas pelo valor total")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de compras retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/compras", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CompraResponseDTO>> listarComprasOrdenadas();

    @Operation(summary = "Obter maior compra por ano", description = "Retorna a maior compra realizada no ano especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maior compra retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/maior-compra/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompraResponseDTO> getMaiorCompraPorAno(@PathVariable int ano);
}
