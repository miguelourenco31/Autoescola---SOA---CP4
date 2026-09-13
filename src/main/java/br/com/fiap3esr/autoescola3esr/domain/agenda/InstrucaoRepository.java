package br.com.fiap3esr.autoescola3esr.domain.agenda;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
boolean existsByInstrutorIdAndDataHoraAndCanceladaFalse(
        Long idInstrutor,
        LocalDateTime dataHora
);

   boolean existsByAlunoIdAndDataHoraBetweenAndCanceladaFalse(
        Long idAluno,
        LocalDateTime inicioExpediente,
        LocalDateTime fimExpediente
);

long countByAlunoIdAndDataHoraBetweenAndCanceladaFalse(
        Long idAluno,
        LocalDateTime inicioExpediente,
        LocalDateTime fimExpediente
);

}