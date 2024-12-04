package com.denteconvenio.planoservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.DiaDaSemana;
import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamento;

public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamento, UUID>{

    boolean existsByDiaSemanaAndCoberturaId(DiaDaSemana diaSemana, UUID idConsultorio);

    Optional<HorarioFuncionamento> findByDiaSemanaAndCoberturaId(DiaDaSemana diaSemana, UUID idConsultorio);

    
}
