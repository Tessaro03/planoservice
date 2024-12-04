package com.denteconvenio.planoservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.denteconvenio.planoservice.domain.beneficio.Beneficio;
import com.denteconvenio.planoservice.domain.plano.Plano;
import com.denteconvenio.planoservice.domain.plano.PlanoRequestDTO;
import com.denteconvenio.planoservice.domain.plano.PlanoResponseDTO;
import com.denteconvenio.planoservice.domain.plano.TipoPlano;
import com.denteconvenio.planoservice.infra.rest.UserServiceClient;
import com.denteconvenio.planoservice.infra.security.TokenService;
import com.denteconvenio.planoservice.repository.AssinaturaRepository;
import com.denteconvenio.planoservice.repository.BeneficioRepository;
import com.denteconvenio.planoservice.repository.PlanoRepository;

import jakarta.servlet.http.HttpServletRequest;
;

@Service
public class PlanoService {
    
    @Autowired
    private PlanoRepository repository;

    @Autowired
    private BeneficioRepository beneficioRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private TokenService tokenService;
    
    @Cacheable(value = "planos")
    public List<PlanoResponseDTO> verPlanos(HttpServletRequest request){

        if (request.getHeader("Authorization") != null) {
            var userRequest = tokenService.extrairInformacoes(request);
            if ("Empresa".equals(userRequest.tipo())) {
                var planos = repository.findAllByTipo(TipoPlano.Empresa);
                return planos.stream().map(PlanoResponseDTO::new).collect(Collectors.toList());
            }
            if ("Beneficiario".equals(userRequest.tipo())) {
                var token = tokenService.recuperarToken(request);
                var idEmpresa = userServiceClient.consultarBeneficiario(token);
                if (idEmpresa != null) {
                    var assinaturaEmpresa = assinaturaRepository.findByIdAssinante(idEmpresa);
                    if (assinaturaEmpresa.isPresent()) {
                        var planos = repository.findAllByTipo(TipoPlano.Beneficiario);
                        return planos.stream()
                                    .peek(plano -> plano.setValor(plano.getValor() * (1 - assinaturaEmpresa.get().getPlano().getDesconto()))) 
                                    .map(PlanoResponseDTO::new)                                     
                                    .collect(Collectors.toList());
                    }
                }
            }
        }                
        var planos = repository.findAllByTipo(TipoPlano.Beneficiario);
        return planos.stream().map(PlanoResponseDTO::new).collect(Collectors.toList());
    }
    
    @CacheEvict(value = "planos", allEntries = true)
    public PlanoResponseDTO criarPlano(PlanoRequestDTO dto) {
        var plano = new Plano(dto.valor(),dto.titulo(),dto.descricao(), dto.tipo(), dto.desconto());
        if (dto.beneficios() != null) {
            var beneficios = listarBeneficios(dto.beneficios());
            plano.setBeneficios(beneficios);
        }
        repository.save(plano);
        return new PlanoResponseDTO(plano);
    }

    @CacheEvict(value = "planos", allEntries = true)
    public PlanoResponseDTO alterarPlano(UUID id, PlanoRequestDTO dto){
        var plano = repository.getReferenceById(id);
        if (dto.titulo() != null) {
            plano.setTitulo(dto.titulo());
        }
        if (dto.descricao() != null) {
            plano.setDescricao(dto.descricao());
        }
        if (dto.valor() != null)  {
            plano.setValor(dto.valor());
        }
        if (dto.beneficios() != null) {
            var beneficios = listarBeneficios(dto.beneficios());
            plano.setBeneficios(beneficios);
        }
        if (dto.tipo() != null) {
            plano.setTipo(dto.tipo());
        }
        if (dto.desconto() != null & plano.getTipo() == TipoPlano.Empresa ){
            plano.setDesconto(dto.desconto());
        }
        repository.save(plano);
        return new PlanoResponseDTO(plano);
    }
    
    @CacheEvict(value = "planos", allEntries = true)
    public void deletarPlano(UUID id) {
        repository.deleteById(id);
    }
    
    public PlanoResponseDTO adicionarBeneficio(UUID id, List<UUID> idsBeneficio){
        var plano = repository.getReferenceById(id);
        var beneficios = listarBeneficios(idsBeneficio);
        for (var beneficio : beneficios) {
            if (!plano.getBeneficios().contains(beneficio)) {
                plano.getBeneficios().add(beneficio);
            }
        }
        repository.save(plano);
        return new PlanoResponseDTO(plano);
    }

    public PlanoResponseDTO removerBeneficio(UUID id, List<UUID> idsBeneficio){
        var plano = repository.getReferenceById(id);
        var beneficios = listarBeneficios(idsBeneficio);
        for (var beneficio : beneficios) {
            if (plano.getBeneficios().contains(beneficio)) {
                plano.getBeneficios().remove(beneficio);
            }
        }
        repository.save(plano);
        return new PlanoResponseDTO(plano);
    }
    
    public List<Beneficio> listarBeneficios(List<UUID> ids){
        var beneficios = new ArrayList<Beneficio>();
        for (UUID idBeneficio : ids) {
            var beneficio = beneficioRepository.getReferenceById(idBeneficio);
            beneficios.add(beneficio);                
        }
        return beneficios;
    }

}
