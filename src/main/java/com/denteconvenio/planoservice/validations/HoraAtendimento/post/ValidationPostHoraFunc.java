package com.denteconvenio.planoservice.validations.HoraAtendimento.post;

import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamentoRequestDTO;

public interface ValidationPostHoraFunc {
    

    void validar(HorarioFuncionamentoRequestDTO dto);

}
