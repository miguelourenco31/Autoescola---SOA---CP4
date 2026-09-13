package br.com.fiap3esr.autoescola3esr.controller;

import br.com.fiap3esr.autoescola3esr.domain.aluno.Aluno;
import br.com.fiap3esr.autoescola3esr.domain.aluno.AlunoRepository;
import br.com.fiap3esr.autoescola3esr.domain.aluno.DadosCadastroAluno;
import br.com.fiap3esr.autoescola3esr.domain.aluno.DadosDetalhamentoAluno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoAluno> cadastrar(
            @RequestBody @Valid DadosCadastroAluno dados) {

        Aluno aluno = new Aluno(dados);

        repository.save(aluno);

        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }
}