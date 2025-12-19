package dev.matheus.miniagendamento.core.usecases;

import dev.matheus.miniagendamento.core.entities.Agendamento;

public interface AtualizarAgendamentoUseCase {
    Agendamento execute(Agendamento agendamento);
}
