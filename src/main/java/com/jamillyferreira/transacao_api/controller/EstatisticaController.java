package com.jamillyferreira.transacao_api.controller;

import com.jamillyferreira.transacao_api.controller.dtos.EstatisticaResponseDTO;
import com.jamillyferreira.transacao_api.service.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
@Tag(name = "Estatistica", description = "API de gerenciamente de estatísticas")

public class EstatisticaController {
    private final EstatisticaService estatisticaService;

    @GetMapping
    @Operation(
            summary = "Buscar Estatísticas",
            description = "Enpoint reponsável por buscar estatísticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatística de transações")
    })
    public ResponseEntity<EstatisticaResponseDTO> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca",
                    required = false,
                    defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticaService.calcularEstatisticaTransacao(intervaloBusca));
    }


}
