package com.denteconvenio.planoservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denteconvenio.planoservice.domain.assinatura.AssinaturaRequestDTO;
import com.denteconvenio.planoservice.service.AssinaturaService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("assinatura")
public class AssinaturaController {
    
    @Autowired
    private AssinaturaService service;

    @GetMapping
    public ResponseEntity verAssinatura(HttpServletRequest request) {
        return ResponseEntity.ok(service.verAssinatura(request));
    }
    
    @PostMapping
    public void assinarPlano(HttpServletRequest request,@RequestBody AssinaturaRequestDTO dto) {
        service.assinarPlano(request, dto);
    }
    
    @DeleteMapping
    public void cancelarAssinatura(HttpServletRequest request){
        service.cancelarAssinatura(request);
    }

    @PatchMapping
    public void renovarAssinatura(HttpServletRequest request,@RequestBody AssinaturaRequestDTO dto) {
        service.renovarAssinatura(request, dto);
    }

    @PostMapping("/{idBeneficio}")
    public Boolean usuarioPodeUsarBeneficio(HttpServletRequest request, @PathVariable UUID idBeneficio) {
        return service.usuarioPodeUsarBeneficio(request, idBeneficio);
    }
    

}
