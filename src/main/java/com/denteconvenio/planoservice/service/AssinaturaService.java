package com.denteconvenio.planoservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.denteconvenio.planoservice.domain.assinatura.Assinatura;
import com.denteconvenio.planoservice.domain.assinatura.AssinaturaRequestDTO;
import com.denteconvenio.planoservice.domain.assinatura.AssinaturaResponseDTO;
import com.denteconvenio.planoservice.infra.security.TokenService;
import com.denteconvenio.planoservice.repository.AssinaturaRepository;
import com.denteconvenio.planoservice.repository.BeneficioRepository;
import com.denteconvenio.planoservice.repository.PlanoRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AssinaturaService {

    @Autowired
    private AssinaturaRepository repository;

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BeneficioRepository beneficioRepository;

    @CacheEvict(value = "planos", allEntries = true)
    public void assinarPlano(HttpServletRequest request, AssinaturaRequestDTO dto){
        var user = tokenService.extrairInformacoes(request);
        var plano = planoRepository.getReferenceById(dto.idPlano());
        var assinatura = new Assinatura(plano, user.id(), dto.validade());
        repository.save(assinatura);
    }

    public void cancelarAssinatura(HttpServletRequest request){
        var user = tokenService.extrairInformacoes(request);
        var  assinatura = repository.findByIdAssinante(user.id());
        if (assinatura.isPresent()) {
            assinatura.get().setAtivo(false);
            repository.save(assinatura.get());
        }
    }

    @Scheduled(cron = "0 59 23 * * ?",zone="America/Sao_Paulo") // Executa diariamente
    public void removerAssinaturasExpiradas() {
        List<Assinatura> assinaturasExpiradas = repository.findAllByValidadeBeforeAndAtivoTrue(LocalDateTime.now());
        assinaturasExpiradas.forEach(a -> a.setAtivo(false));
        repository.saveAll(assinaturasExpiradas);
    }

    public AssinaturaResponseDTO verAssinatura(HttpServletRequest request){
        var user = tokenService.extrairInformacoes(request);
        var assinatura = repository.findByIdAssinante(user.id());
        return new AssinaturaResponseDTO(assinatura.get());
    }

    public AssinaturaResponseDTO renovarAssinatura(HttpServletRequest request, AssinaturaRequestDTO dto){
        var user = tokenService.extrairInformacoes(request);
        var assinatura = repository.findByIdAssinante(user.id());
        if (assinatura.isPresent()) {
            assinatura.get().setValidade(assinatura.get().getValidade().plusMonths(dto.validade()));
            repository.save(assinatura.get());
            return new AssinaturaResponseDTO(assinatura.get());
        }
        return null;
    }

    public boolean usuarioPodeUsarBeneficio(HttpServletRequest request, UUID idBeneficio) {
        var user = tokenService.extrairInformacoes(request);
        var assinatura = repository.findByIdAssinante(user.id());
        if (assinatura.isPresent()) {
            if (assinatura.get().getPlano().getBeneficios().contains(beneficioRepository.getReferenceById(idBeneficio))) {
                return true;
            }
        }
        return false;
    }

}
