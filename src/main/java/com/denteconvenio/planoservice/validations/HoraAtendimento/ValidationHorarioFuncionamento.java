package com.denteconvenio.planoservice.validations.HoraAtendimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamento;
import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamentoRequestDTO;
import com.denteconvenio.planoservice.validations.HoraAtendimento.patch.ValidationPatchHoraFunc;
import com.denteconvenio.planoservice.validations.HoraAtendimento.post.ValidationPostHoraFunc;

@Service
public class ValidationHorarioFuncionamento {
    
    @Autowired
    private List<ValidationPostHoraFunc> validationPostHoraFuncs;

    @Autowired
    private List<ValidationPatchHoraFunc> validationPatchHoraFuncs;

    public void validarPost(HorarioFuncionamentoRequestDTO dto){
        validationPostHoraFuncs.forEach(v -> v.validar(dto));
    }

    public void validarPatch(HorarioFuncionamento horario,HorarioFuncionamentoRequestDTO dto){
        validationPatchHoraFuncs.forEach(v -> v.validar( horario ,dto));
    }


}
