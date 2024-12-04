package com.denteconvenio.planoservice.validations.HoraAtendimento.post;

import org.springframework.stereotype.Component;

import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamentoRequestDTO;
import com.denteconvenio.planoservice.infra.validation.ValidacaoException;

@Component
public class ValidandoIntevaloDeHoras implements ValidationPostHoraFunc{

    @Override
    public void validar(HorarioFuncionamentoRequestDTO dto) {
        if (dto.horarioAbertura().isAfter(dto.horarioFechamento())) {
            throw new ValidacaoException("Horario de fechamento deve ser depois após horario de abertura");
            
        }
    }
    
}
