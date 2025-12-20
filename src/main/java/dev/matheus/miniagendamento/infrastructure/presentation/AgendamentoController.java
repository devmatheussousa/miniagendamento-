package dev.matheus.miniagendamento.infrastructure.presentation;

import dev.matheus.miniagendamento.core.entities.Agendamento;
import dev.matheus.miniagendamento.core.usecases.*;
import dev.matheus.miniagendamento.infrastructure.dto.AgendamentoCreateRequest;
import dev.matheus.miniagendamento.infrastructure.dto.AgendamentoResponse;
import dev.matheus.miniagendamento.infrastructure.dto.AgendamentoUpdateRequest;
import dev.matheus.miniagendamento.infrastructure.mapper.AgendamentoCreateMapper;
import dev.matheus.miniagendamento.infrastructure.mapper.AgendamentoReponseMapper;
import dev.matheus.miniagendamento.infrastructure.mapper.AgendamentoUpdateRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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



    @PostMapping("/criar")
    public ResponseEntity<Map<String, Object>> criarAgendamento(AgendamentoCreateRequest request) {
        Agendamento criado = criarAgendamentoUseCase.execute(agendamentoCreateMapper.toEntity(request));
        Map<String, Object> response = new HashMap<>();
        response.put("agendamento ", "criado com sucesso");
        response.put("agendamento", agendamentoCreateMapper.toDto(criado));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AgendamentoResponse> buscarAgendamentoPorId(Long id) {
        return ResponseEntity.ok(agendamentoReponseMapper.toDto(buscarAgendamentoPorIdUseCase.execute(id)));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Map<String, Object>> atualizarPorId(@PathVariable Long id, @RequestBody AgendamentoUpdateRequest request) {

        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);

        Agendamento atualizado = atualizarAgendamentoUseCase.execute(agendamentoUpdateRequestMapper.merge(existente, request));
        Map<String, Object> response = new HashMap<>();
        response.put("agendamento", agendamentoReponseMapper.toDto(atualizado));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Map<String, Object>> cancelarPorId(@PathVariable Long id) {
        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);

        if(existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento cancelado = cancelarAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento cancelador com sucesso");
        response.put("agendamento", agendamentoReponseMapper.toDto(cancelado));
        return ResponseEntity.ok(response);
    }


    @PutMapping("/concluir/{id}")
    public ResponseEntity<Map<String, Object>> concluirPorId(@PathVariable Long id) {
        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);

        if(existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento concluido = concluirAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento concluído com sucesso");
        response.put("agendamento", agendamentoReponseMapper.toDto(concluido));
        return ResponseEntity.ok(response);
    }
}
