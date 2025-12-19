package dev.matheus.miniagendamento.core.usecases;

import dev.matheus.miniagendamento.core.entities.Agendamento;
import dev.matheus.miniagendamento.core.gateway.AgendamentoGateway;

public class ConcluirAgendamentoUseCaseimpl implements  ConcluirAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ConcluirAgendamentoUseCaseimpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Long id) {
        if(agendamentoGateway.buscarAgendamentoPorId(id) == null){
            throw new IllegalArgumentException("Agendamento não encontrado");
        }
        return agendamentoGateway.concluirAgendamento(id);
    }
}
