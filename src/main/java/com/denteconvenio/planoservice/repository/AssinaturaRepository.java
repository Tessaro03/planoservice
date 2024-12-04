package com.denteconvenio.planoservice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denteconvenio.planoservice.domain.assinatura.Assinatura;
import com.google.common.base.Optional;

public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {

    Optional<Assinatura> findByIdAssinante(UUID id);

    List<Assinatura> findAllByValidadeBeforeAndAtivoTrue(LocalDateTime now);

}
