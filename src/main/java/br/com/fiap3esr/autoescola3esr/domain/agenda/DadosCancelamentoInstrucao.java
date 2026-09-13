package br.com.fiap3esr.autoescola3esr.domain.agenda;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoInstrucao(

        @NotNull
        Long idInstrucao,

        @NotNull
        MotivoCancelamento motivo

) {
}