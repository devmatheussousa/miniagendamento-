package dev.matheus.miniagendamento.infrastructure.gateway;

import dev.matheus.miniagendamento.core.entities.Agendamento;
import dev.matheus.miniagendamento.core.enums.StatusAgendamento;
import dev.matheus.miniagendamento.core.gateway.AgendamentoGateway;
import dev.matheus.miniagendamento.infrastructure.mapper.AgendamentoEntityMapper;
import dev.matheus.miniagendamento.infrastructure.persistence.AgendamentoEntity;
import dev.matheus.miniagendamento.infrastructure.persistence.AgendamentoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoRepositoryGateway implements AgendamentoGateway {

    private final AgendamentoRepository repository;
    private final AgendamentoEntityMapper mapper;

    public AgendamentoRepositoryGateway(AgendamentoRepository repository, AgendamentoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Agendamento criarAgendamento(Agendamento agendamento) {

        validarIntervalo(agendamento.dataInicio(), agendamento.dataFim());
        checarConflito(agendamento.usuario(), agendamento.dataInicio(), agendamento.dataFim(), agendamento.id());

        AgendamentoEntity agendamentoEntity = repository.save(mapper.toEntity(agendamento));

            return mapper.toDomain(agendamentoEntity);
    }

    @Override
    public Agendamento buscarAgendamentoPorId(Long id) {

        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        return mapper.toDomain(repository.save(mapper.toEntity(agendamento)));
    }

    @Override
    public Agendamento cancelarAgendamento(Long id) {
        var existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        existente.setStatus(StatusAgendamento.CANCELADO);
        existente.setAtualizadoEm(LocalDateTime.now());
        return mapper.toDomain(repository.save(existente));
    }

    @Override
    public Agendamento concluirAgendamento(Long id) {
        var existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        existente.setStatus(StatusAgendamento.CONCLUIDO);
        existente.setAtualizadoEm(LocalDateTime.now());
        return mapper.toDomain(repository.save(existente));
    }


    private void validarIntervalo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null || !inicio.isBefore(fim)) { // se a data de início for depois da data de fim
            throw new IllegalArgumentException("Data de início deve ser anterior à data de fim");
        }
    }

    private void checarConflito(String usuario, LocalDateTime inicio, LocalDateTime fim, Long usuarioId) {
        boolean conflito = repository.existsConflito(usuario, inicio, fim, usuarioId);
        if (conflito) {
            throw new IllegalArgumentException("Conflito de agendamento com outro agendamento do mesmo usuário");
        }
    }



}
//27:31