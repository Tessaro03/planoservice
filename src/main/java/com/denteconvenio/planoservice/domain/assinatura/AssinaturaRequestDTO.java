package com.denteconvenio.planoservice.domain.assinatura;

import java.util.UUID;

public record AssinaturaRequestDTO(

    UUID idPlano,
    UUID idAssinante,
    Long validade

) {
    

}
