package com.denteconvenio.planoservice.domain.plano;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.denteconvenio.planoservice.domain.beneficio.BeneficioResponseDTO;

public record PlanoResponseDTO (

    UUID id,
    Double valor,
    String titulo,
    String descricao,
    TipoPlano tipo,
    Double desconto,
    List<BeneficioResponseDTO> beneficios

) implements Serializable{

    public PlanoResponseDTO(Plano plano) {
        this(plano.getId(), plano.getValor(), plano.getTitulo(), plano.getDescricao(), plano.getTipo(), plano.getDesconto(), 
            plano.getBeneficios().stream().map(BeneficioResponseDTO::new).collect(Collectors.toList()));
    }

}
