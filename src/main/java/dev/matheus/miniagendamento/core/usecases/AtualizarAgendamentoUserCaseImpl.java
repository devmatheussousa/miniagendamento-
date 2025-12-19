package dev.matheus.miniagendamento.core.usecases;

import dev.matheus.miniagendamento.core.entities.Agendamento;
import dev.matheus.miniagendamento.core.gateway.AgendamentoGateway;

import java.time.LocalDateTime;

public class AtualizarAgendamentoUserCaseImpl implements AtualizarAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public AtualizarAgendamentoUserCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Agendamento agendamento) {
        var existente = agendamentoGateway.buscarAgendamentoPorId(agendamento.id());
        if(existente == null) {
            throw new IllegalArgumentException("Agendamento não encontrado");
        }
        return agendamentoGateway.atualizarAgendamento(new Agendamento(
                existente.id(),
                existente.titulo(),
                existente.descricao(),
                existente.dataInicio(),
                existente.dataFim(),
                existente.status(),
                existente.usuario(),
                existente.criadoEm(),
                LocalDateTime.now()
                ));
    }
}
