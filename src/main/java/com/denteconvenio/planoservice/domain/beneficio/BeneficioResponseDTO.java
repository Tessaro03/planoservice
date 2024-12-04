package com.denteconvenio.planoservice.domain.beneficio;

import java.util.UUID;
import java.io.Serializable;

public record BeneficioResponseDTO(

        UUID id,
        String titulo,
        String descricao

) implements Serializable{
    
    public BeneficioResponseDTO(Beneficio beneficio){
        this(beneficio.getId(), beneficio.getTitulo(),beneficio.getDescricao());
    }
}
