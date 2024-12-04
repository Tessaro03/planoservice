package com.denteconvenio.planoservice.validations.HoraAtendimento.patch;

import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamento;
import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamentoRequestDTO;

public interface ValidationPatchHoraFunc {
    

    void validar(HorarioFuncionamento horario,HorarioFuncionamentoRequestDTO dto);

}
