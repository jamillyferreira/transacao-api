package com.jamillyferreira.transacao_api.service;

import com.jamillyferreira.transacao_api.controller.dtos.EstatisticaResponseDTO;
import com.jamillyferreira.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {
    private final TransacaoService transacaoService;

    public EstatisticaResponseDTO calcularEstatisticaTransacao(Integer intervaloBusca) {
        log.info("Iniciando busca de estatística de transações pelo período de tempo: " + intervaloBusca);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacao(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticaResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes
                .stream()
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        log.info("Estatísticas retornadas com sucesso");
        return new EstatisticaResponseDTO(
                estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
