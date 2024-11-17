package br.com.desafio.infrastructure.entrypoint.api.controller;

import br.com.desafio.application.dto.RecomendacaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "Recomendacao Controller", description = "Endpoints para obter recomendação de vinhos")
public interface RecomendacaoController {

    @Operation(summary = "Obter recomendação de vinho", description = "Retorna a recomendação de vinho para o cliente com o CPF fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recomendação de vinho retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/{cpf}/tipo", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RecomendacaoResponseDTO> getRecomendacaoVinho(@PathVariable String cpf);
}
