package com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento;

import java.time.LocalTime;

public record HorarioFuncionamentoResponseDTO(


    String diaSemana, 
    LocalTime horarioAbertura,
    LocalTime horarioFechamento
) {
    
    public HorarioFuncionamentoResponseDTO(HorarioFuncionamento horario){
        this(horario.getDiaSemana().toString(), horario.getHorarioAbertura(),horario.getHorarioFechamento());
    }

}
