package dev.matheus.miniagendamento.infrastructure.mapper;

import dev.matheus.miniagendamento.core.entities.Agendamento;
import dev.matheus.miniagendamento.infrastructure.dto.AgendamentoResponse;
import dev.matheus.miniagendamento.infrastructure.persistence.AgendamentoEntity;
import org.springframework.stereotype.Component;

@Component //Esse componente é responsável por mapear a entidade AgendamentoEntity para o DTO AgendamentoResponse
public class AgendamentoReponseMapper {

    public AgendamentoResponse toDto(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.id(),
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.status(),
                agendamento.usuario(),
                agendamento.criadoEm(),
                agendamento.atualizadoEm()
        );
    }

    public Agendamento toEntity(AgendamentoResponse agendamentoResponse) {
        return new Agendamento(
                agendamentoResponse.id(),
                agendamentoResponse.titulo(),
                agendamentoResponse.descricao(),
                agendamentoResponse.dataInicio(),
                agendamentoResponse.dataFim(),
                agendamentoResponse.status(),
                agendamentoResponse.usuario(),
                agendamentoResponse.criadoEm(),
                agendamentoResponse.atualizadoEm()
        );
    }
}
