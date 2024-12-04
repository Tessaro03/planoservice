package com.denteconvenio.planoservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denteconvenio.planoservice.domain.beneficio.Beneficio;
import com.denteconvenio.planoservice.domain.cobertura.Cobertura;
import com.denteconvenio.planoservice.domain.cobertura.CoberturaRequestDTO;
import com.denteconvenio.planoservice.domain.cobertura.CoberturaResponseDTO;
import com.denteconvenio.planoservice.infra.security.TokenService;
import com.denteconvenio.planoservice.repository.BeneficioRepository;
import com.denteconvenio.planoservice.repository.CoberturaRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CoberturaService {

    @Autowired
    private CoberturaRepository repository;

    @Autowired
    private BeneficioRepository beneficioRepository;


    @Autowired 
    private TokenService tokenService;

    public CoberturaResponseDTO verCoberturaConsultorio(UUID idConsultorio) {
        var cobertura = repository.findByIdConsultorio(idConsultorio);
        return new CoberturaResponseDTO(cobertura);

    }

    public void criarCobertura(CoberturaRequestDTO dto){
        List<Beneficio> beneficios = new ArrayList<>();
        if (dto.idBeneficios() != null) {
            beneficios = dto.idBeneficios().stream().map(b -> beneficioRepository.getReferenceById(b)).collect(Collectors.toList());
        }
        var cobertura = new Cobertura(dto.idConsultorio(),beneficios);
        repository.save(cobertura);
    }

    public CoberturaResponseDTO adicionarBeneficio(HttpServletRequest request, CoberturaRequestDTO dto){
        var consultorioToken = tokenService.extrairInformacoes(request);
        var cobertura = repository.findByIdConsultorio(consultorioToken.id());
        for (UUID idBeneficio  : dto.idBeneficios()) {
            var beneficio = beneficioRepository.getReferenceById(idBeneficio);
            if (!cobertura.getBeneficios().contains(beneficio)) {
                cobertura.getBeneficios().add(beneficio);
            }
            repository.save(cobertura);
        }
        return new CoberturaResponseDTO(cobertura);
    }

    public CoberturaResponseDTO removerBeneficio(HttpServletRequest request, CoberturaRequestDTO dto){
        var consultorioToken = tokenService.extrairInformacoes(request);
        var cobertura = repository.findByIdConsultorio(consultorioToken.id());
        for (UUID idBeneficio  : dto.idBeneficios()) {
            var beneficio = beneficioRepository.getReferenceById(idBeneficio);
            if (cobertura.getBeneficios().contains(beneficio)) {
                cobertura.getBeneficios().remove(beneficio);
            }
            repository.save(cobertura);
        }
        return new CoberturaResponseDTO(cobertura);    
    }

    public boolean consultorioCobreBeneficio(UUID idConsultorio, UUID idBeneficio) {
        var cobertura = repository.findByIdConsultorio(idConsultorio);
        var beneficio = beneficioRepository.findById(idBeneficio);
        if (cobertura.getBeneficios().contains(beneficio)) {
            return true;
        }
        return false;
    }

}
