package br.com.fiap3esr.autoescola3esr.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAlteracaoSenha(

        @NotBlank
        String senhaAtual,

        @NotBlank
        String novaSenha

) {
}