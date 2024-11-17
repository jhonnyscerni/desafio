package br.com.desafio.infrastructure.entrypoint.api.controller;

import br.com.desafio.application.dto.ClienteFielResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(name = "Cliente Controller", description = "Endpoints para obter informações dos clientes")
public interface ClienteController {

    @Operation(summary = "Obter clientes fiéis", description = "Retorna o Top 3 dos clientes mais fieis,")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes fiéis retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/clientes-fieis", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ClienteFielResponseDTO>> getClientesFieis();
}
