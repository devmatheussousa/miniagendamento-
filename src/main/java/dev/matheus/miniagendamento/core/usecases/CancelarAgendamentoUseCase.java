package dev.matheus.miniagendamento.core.usecases;

import dev.matheus.miniagendamento.core.entities.Agendamento;

public interface CancelarAgendamentoUseCase {

    Agendamento execute(Long id);
}
