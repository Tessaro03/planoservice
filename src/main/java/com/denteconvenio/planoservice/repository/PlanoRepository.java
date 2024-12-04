package com.denteconvenio.planoservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denteconvenio.planoservice.domain.plano.Plano;
import com.denteconvenio.planoservice.domain.plano.TipoPlano;

public interface PlanoRepository extends JpaRepository<Plano, UUID>{

    List<Plano> findAllByTipo(TipoPlano empresa);
    
}
