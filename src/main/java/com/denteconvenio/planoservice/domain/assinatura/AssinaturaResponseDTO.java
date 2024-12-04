package com.denteconvenio.planoservice.domain.assinatura;

import java.time.LocalDateTime;
import java.util.UUID;

import com.denteconvenio.planoservice.domain.plano.PlanoResponseDTO;

public record AssinaturaResponseDTO(

    UUID id,
    PlanoResponseDTO plano,
    UUID idAssinante,
    LocalDateTime validade,
    Boolean ativo
) {
    

    public AssinaturaResponseDTO(Assinatura assinatura){
        this(assinatura.getId(),new PlanoResponseDTO(assinatura.getPlano()), assinatura.getIdAssinante(), assinatura.getValidade(),assinatura.getAtivo());
    }

}
