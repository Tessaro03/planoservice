package com.denteconvenio.planoservice.validations.HoraAtendimento.patch;

import org.springframework.stereotype.Component;

import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamento;
import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamentoRequestDTO;
import com.denteconvenio.planoservice.infra.validation.ValidacaoException;

@Component
public class ValidandoIntevaloDeHorasPatch implements ValidationPatchHoraFunc{



    @Override
    public void validar(HorarioFuncionamento horario, HorarioFuncionamentoRequestDTO dto) {

        if (dto.horarioAbertura() != null && horario.getHorarioFechamento().isBefore(dto.horarioAbertura())) {
            throw new ValidacaoException("Horario de abertura deve ser antes do horario de fechamento");
            
        }

        if (dto.horarioFechamento() != null && horario.getHorarioAbertura().isAfter(dto.horarioFechamento())) {
            throw new ValidacaoException("Horario de fechamento deve ser depois do horario de abertura");
        }


        if (dto.horarioFechamento() != null && dto.horarioAbertura() != null && dto.horarioAbertura().isAfter(dto.horarioFechamento())) {
            throw new ValidacaoException("Horario de fechamento deve ser depois do horario de abertura");
            
        }
    }
    
}
