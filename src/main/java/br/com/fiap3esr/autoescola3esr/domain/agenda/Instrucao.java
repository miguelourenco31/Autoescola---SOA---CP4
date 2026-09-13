package br.com.fiap3esr.autoescola3esr.domain.agenda;

import br.com.fiap3esr.autoescola3esr.domain.aluno.Aluno;
import br.com.fiap3esr.autoescola3esr.domain.instrutor.Instrutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Instrucao")
@Table(name = "instrucoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Instrucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    @Column(name = "data_hora")
    LocalDateTime dataHora;
    
    private boolean cancelada;

@Column(name = "motivo_cancelamento")
private String motivoCancelamento;

public void cancelar(MotivoCancelamento motivo) {

    if (LocalDateTime.now().plusHours(24).isAfter(this.dataHora)) {
        throw new ValidacaoException(
                "A instrução só pode ser cancelada com no mínimo 24 horas de antecedência!"
        );
    }

    this.cancelada = true;
    this.motivoCancelamento = motivo.name();
}

}