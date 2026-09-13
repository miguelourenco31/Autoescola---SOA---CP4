package br.com.fiap3esr.autoescola3esr.service;

import br.com.fiap3esr.autoescola3esr.domain.agenda.*;
import br.com.fiap3esr.autoescola3esr.domain.agenda.validacao.ValidadorAgendamento;
import br.com.fiap3esr.autoescola3esr.domain.agenda.validacao.ValidadorInstrutorAtivo;
import br.com.fiap3esr.autoescola3esr.domain.aluno.Aluno;
import br.com.fiap3esr.autoescola3esr.domain.aluno.AlunoNotFoundException;
import br.com.fiap3esr.autoescola3esr.domain.aluno.AlunoRepository;
import br.com.fiap3esr.autoescola3esr.domain.instrutor.Instrutor;
import br.com.fiap3esr.autoescola3esr.domain.instrutor.InstrutorNotFoundException;
import br.com.fiap3esr.autoescola3esr.domain.instrutor.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeInstrucoes {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private InstrucaoRepository repository;

    @Autowired
    private List<ValidadorAgendamento> validadoresAgendamento;

    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados) {
        if (!alunoRepository.existsById(dados.idAluno())) {
            throw new AlunoNotFoundException("ID do aluno informado não existe!");
        }
        if (dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new InstrutorNotFoundException("ID do instrutor informado não existe!");
        }
        //Validações
        validadoresAgendamento.forEach(validador -> validador.validar(dados));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());

        Instrutor instrutor = escolherInstrutor(dados);
        if (instrutor == null) {
            throw new ValidacaoException("Não existe instrutor disponível para a data/hora informada!");
        }
Instrucao instrucao = new Instrucao(
        null,
        aluno,
        instrutor,
        dados.dataHora(),
        false,
        null
);
        Instrucao salva = repository.save(instrucao);
        return new DadosDetalhamentoAgendamento(salva);
    }

    public void cancelar(DadosCancelamentoInstrucao dados) {

    if (!repository.existsById(dados.idInstrucao())) {
        throw new ValidacaoException("ID da instrução informado não existe!");
    }

    Instrucao instrucao = repository.getReferenceById(dados.idInstrucao());

    if (instrucao.isCancelada()) {
        throw new ValidacaoException("Esta instrução já foi cancelada!");
    }

    instrucao.cancelar(dados.motivo());

    repository.save(instrucao);
}

    private Instrutor escolherInstrutor(DadosAgendamento dados) {
        if (dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é campo obrigatório, caso o instrutor não seja informado!");
        }
        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dados.especialidade(), dados.dataHora());
    }
}