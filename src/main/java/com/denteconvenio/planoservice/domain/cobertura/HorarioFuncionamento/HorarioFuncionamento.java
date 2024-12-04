package com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento;

import java.time.LocalTime;
import java.util.UUID;

import com.denteconvenio.planoservice.domain.cobertura.Cobertura;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="horariofuncionamento")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HorarioFuncionamento {

    @Id
    @GeneratedValue( strategy= GenerationType.UUID)
    private UUID id;

    private DiaDaSemana diaSemana;

    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;

    @ManyToOne
    @JoinColumn(name = "cobertura_id")     
    private Cobertura cobertura;

    public HorarioFuncionamento(DiaDaSemana diaSemana, LocalTime horarioAbertura, LocalTime horarioFechamento, Cobertura cobertura) {
        this.diaSemana = diaSemana;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.cobertura = cobertura;
    }
}

