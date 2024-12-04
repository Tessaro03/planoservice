package com.denteconvenio.planoservice.domain.cobertura;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.denteconvenio.planoservice.domain.beneficio.BeneficioResponseDTO;
import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamentoResponseDTO;

public record CoberturaResponseDTO(

        UUID idConsultorio,
        List<BeneficioResponseDTO> beneficios,
        List<HorarioFuncionamentoResponseDTO> horarios
) {
    
        public CoberturaResponseDTO(Cobertura cobertura){
                this(cobertura.getIdConsultorio(), cobertura.getBeneficios().stream().map(BeneficioResponseDTO::new).collect(Collectors.toList()), 
                cobertura.getHorarioFuncionamento().stream().map(HorarioFuncionamentoResponseDTO::new).collect(Collectors.toList()));
        }
}
