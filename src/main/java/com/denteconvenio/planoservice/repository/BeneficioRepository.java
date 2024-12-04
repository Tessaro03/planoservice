package com.denteconvenio.planoservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.denteconvenio.planoservice.domain.beneficio.Beneficio;

public interface BeneficioRepository extends JpaRepository<Beneficio, UUID> {

    boolean existsByTitulo(String string);

    Optional<Beneficio> findByTitulo(String titulo);
    
    @Modifying
    @Query(value = "DELETE FROM planos_beneficios WHERE beneficios_id = :id", nativeQuery = true)
    void deletarBeneficioDosPlanos(UUID id);
}
