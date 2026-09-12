package br.com.fiap3esr.autoescola3esr.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(

        @NotBlank
        String login,

        @NotBlank
        String senha,

        @NotNull
        Role perfil

) {
}
