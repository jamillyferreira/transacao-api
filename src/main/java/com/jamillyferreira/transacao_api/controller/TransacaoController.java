package com.jamillyferreira.transacao_api.controller;

import com.jamillyferreira.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.jamillyferreira.transacao_api.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
@Tag(name = "Transacao", description = "API de gerenciamente de transações")
public class TransacaoController {
    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(
            summary = "Adicionar transação",
            description = "Endpoint responsável por adicionar uma nova transação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação gravada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionarTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(
            summary = "Deletar todas as transações",
            description = "Remove todas as transações do sistema em memória"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações deletadas coms sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limparTransacao();
        return ResponseEntity.ok().build();
    }
}
