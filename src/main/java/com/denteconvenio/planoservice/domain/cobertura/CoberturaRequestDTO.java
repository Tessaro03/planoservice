package com.denteconvenio.planoservice.domain.cobertura;

import java.util.List;
import java.util.UUID;

public record CoberturaRequestDTO(

    UUID idConsultorio,
    List<UUID> idBeneficios

) {
    
}
