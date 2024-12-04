package com.denteconvenio.planoservice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.denteconvenio.planoservice.domain.beneficio.Beneficio;
import com.denteconvenio.planoservice.domain.beneficio.BeneficioRequestDTO;
import com.denteconvenio.planoservice.domain.beneficio.BeneficioResponseDTO;
import com.denteconvenio.planoservice.repository.BeneficioRepository;

import jakarta.transaction.Transactional;

@Service
public class BeneficioService {


    @Autowired
    private BeneficioRepository repository;

    @Cacheable(value = "beneficios")
    public List<BeneficioResponseDTO> verBeneficios(Pageable pageable){
        var beneficios = repository.findAll(pageable);
        return beneficios.stream().map(BeneficioResponseDTO::new).collect(Collectors.toList());
    }

    @CacheEvict(value = "beneficios", allEntries = true)
    public BeneficioResponseDTO criarBeneficio(BeneficioRequestDTO dto){
        var beneficio = new Beneficio(dto.titulo(), dto.descricao());
        repository.save(beneficio);
        return new BeneficioResponseDTO(beneficio);
    }

    @CacheEvict(value = "beneficios", allEntries = true)
    public BeneficioResponseDTO alterarBeneficio(UUID id,BeneficioRequestDTO dto){
        var beneficio = repository.getReferenceById(id);
        if (dto.titulo() != null) {
            beneficio.setTitulo(dto.titulo());
        }
        if (dto.descricao() != null) {
            beneficio.setDescricao(dto.descricao());
        }
        repository.save(beneficio);
        return new BeneficioResponseDTO(beneficio);
    }

    @Transactional
    @CacheEvict(value = "beneficios", allEntries = true)
    public void deletarBeneficio(UUID id){
        repository.deletarBeneficioDosPlanos(id);
        repository.deleteById(id);
    }
}
