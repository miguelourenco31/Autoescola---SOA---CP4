package br.com.fiap3esr.autoescola3esr.domain.aluno;

import br.com.fiap3esr.autoescola3esr.domain.endereco.Endereco;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco,
        boolean ativo
) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getCpf(),
                aluno.getEndereco(),
                aluno.isAtivo()
        );
    }
}
