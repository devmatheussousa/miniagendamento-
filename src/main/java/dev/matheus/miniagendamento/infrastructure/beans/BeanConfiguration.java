package dev.matheus.miniagendamento.infrastructure.beans;

import dev.matheus.miniagendamento.core.gateway.AgendamentoGateway;
import dev.matheus.miniagendamento.core.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CriarAgendamentoUseCase criarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CriarAgendamentoUseCaseImpl(agendamentoGateway);
    }

     @Bean
    public BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase(AgendamentoGateway agendamentoGateway) {
        return new BuscarAgendamentoPorIdUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public AtualizarAgendamentoUseCase atualizarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new AtualizarAgendamentoUserCaseImpl(agendamentoGateway);
    }

    @Bean
    public CancelarAgendamentoUseCase cancelarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CancelarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public ConcluirAgendamentoUseCase concluirAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new ConcluirAgendamentoUseCaseimpl(agendamentoGateway);
    }


}
