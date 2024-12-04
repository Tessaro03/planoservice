package com.denteconvenio.planoservice.domain.plano;

import java.util.List;
import java.util.UUID;

public record PlanoRequestDTO(

    Double valor,
    String titulo,
    String descricao,
    TipoPlano tipo,
    Double desconto,
    List<UUID> beneficios

) {

}
