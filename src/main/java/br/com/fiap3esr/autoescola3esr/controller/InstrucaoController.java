package br.com.fiap3esr.autoescola3esr.controller;

import br.com.fiap3esr.autoescola3esr.domain.agenda.DadosAgendamento;
import br.com.fiap3esr.autoescola3esr.domain.agenda.DadosDetalhamentoAgendamento;
import br.com.fiap3esr.autoescola3esr.domain.agenda.Instrucao;
import br.com.fiap3esr.autoescola3esr.domain.agenda.InstrucaoRepository;
import br.com.fiap3esr.autoescola3esr.domain.aluno.Aluno;
import br.com.fiap3esr.autoescola3esr.domain.aluno.AlunoRepository;
import br.com.fiap3esr.autoescola3esr.domain.instrutor.DadosDetalhamentoInstrutor;
import br.com.fiap3esr.autoescola3esr.domain.instrutor.Instrutor;
import br.com.fiap3esr.autoescola3esr.domain.instrutor.InstrutorRepository;
import br.com.fiap3esr.autoescola3esr.service.AgendaDeInstrucoes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap3esr.autoescola3esr.domain.agenda.DadosCancelamentoInstrucao;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/instrucoes")
public class InstrucaoController {
    @Autowired
    private AgendaDeInstrucoes agenda;

    @PostMapping
    public ResponseEntity agendarInstrucao(@RequestBody @Valid DadosAgendamento dados) {
        return ResponseEntity.ok(agenda.agendar(dados));
    }

    @PutMapping("/cancelamento")
public ResponseEntity<Void> cancelarInstrucao(
        @RequestBody @Valid DadosCancelamentoInstrucao dados) {

    agenda.cancelar(dados);

    return ResponseEntity.noContent().build();
}

}