package com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento;

import java.time.LocalTime;

public record HorarioFuncionamentoRequestDTO(

    DiaDaSemana diaSemana, 
    LocalTime horarioAbertura,
    LocalTime horarioFechamento

) {
    
}
