package dev.matheus.miniagendamento.core.usecases;

import dev.matheus.miniagendamento.core.entities.Agendamento;
import dev.matheus.miniagendamento.core.gateway.AgendamentoGateway;

public class CriarAgendamentoUseCaseImpl implements  CriarAgendamentoUseCase {
    private final AgendamentoGateway agendamentoGateway;

    public CriarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Agendamento agendamento) {
        return agendamentoGateway.criarAgendamento(agendamento);
    }
}
