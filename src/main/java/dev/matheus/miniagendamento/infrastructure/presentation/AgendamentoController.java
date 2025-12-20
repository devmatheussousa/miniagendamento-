package dev.matheus.miniagendamento.infrastructure.presentation;

import dev.matheus.miniagendamento.core.usecases.*;
import dev.matheus.miniagendamento.infrastructure.mapper.AgendamentoCreateMapper;
import dev.matheus.miniagendamento.infrastructure.mapper.AgendamentoReponseMapper;
import dev.matheus.miniagendamento.infrastructure.mapper.AgendamentoUpdateRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase;
    private final AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
    private final CancelarAgendamentoUseCase cancelarAgendamentoUseCase;
    private final ConcluirAgendamentoUseCase concluirAgendamentoUseCase;
    private final AgendamentoCreateMapper agendamentoCreateMapper;
    private final AgendamentoReponseMapper agendamentoReponseMapper;
    private final AgendamentoUpdateRequestMapper agendamentoUpdateRequestMapper;
}
