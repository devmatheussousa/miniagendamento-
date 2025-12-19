package dev.matheus.miniagendamento.core.usecases;

import dev.matheus.miniagendamento.core.entities.Agendamento;

public interface BuscarAgendamentoProIdUseCase {
    Agendamento execute(Long id);
}
