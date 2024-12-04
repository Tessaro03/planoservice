package com.denteconvenio.planoservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denteconvenio.planoservice.domain.cobertura.Cobertura;

public interface CoberturaRepository extends JpaRepository<Cobertura, UUID>{

    Cobertura findByIdConsultorio(UUID id);

    
}
