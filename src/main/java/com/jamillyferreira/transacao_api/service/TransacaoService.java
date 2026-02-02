package com.jamillyferreira.transacao_api.service;

import com.jamillyferreira.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.jamillyferreira.transacao_api.infrastructure.exceptions.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto) {
        log.info("Iniciando o processo de gravação de transações");
        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Transação rejeitada: Data futura informada - {}", dto.dataHora());
            throw new UnprocessableEntityException("Data e Hora maiores que a data e hora atuais");
        }
        if (dto.valor() < 0) {
            log.error("Transação rejeitada: Valor negativo não permitido - {}", dto.valor());
            throw new UnprocessableEntityException("Valor não pode ser menor que 0");
        }
        listaTransacoes.add(dto);
        log.info("Transação adicionada com sucesso!");
    }

    public void limparTransacao() {
        log.info("Limpando todas as transações. Total removido: {}", listaTransacoes.size());
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacao(Integer intervaloBusca) {
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)
                        || transacao.dataHora().isEqual(dataHoraIntervalo))
                .toList();
    }
}
