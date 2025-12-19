package dev.matheus.miniagendamento.core.gateway;

import dev.matheus.miniagendamento.core.entities.Agendamento;

public interface AgendamentoGateway {

    Agendamento criarAgendamento(Agendamento agendamento);
    Agendamento buscarAgendamentoPorId(Long id);
    Agendamento atualizarAgendamento(Agendamento agendamento);
    Agendamento cancelarAgendamento(Long id);
    Agendamento concluirAgendamento(Long id);
}
