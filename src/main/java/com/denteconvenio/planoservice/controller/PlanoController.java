package com.denteconvenio.planoservice.controller;

import java.util.List;
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

import com.denteconvenio.planoservice.domain.plano.PlanoRequestDTO;
import com.denteconvenio.planoservice.service.PlanoService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/plano")
public class PlanoController {

    @Autowired
    private PlanoService service;

    @PostMapping
    public ResponseEntity criarPlano(@RequestBody PlanoRequestDTO dto) {
        return ResponseEntity.ok(service.criarPlano(dto));
    }
    
    @GetMapping
    public ResponseEntity verPlanos( HttpServletRequest request) {
        return ResponseEntity.ok(service.verPlanos( request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity alterarPlano(@PathVariable UUID id, @RequestBody PlanoRequestDTO dto){
        return ResponseEntity.ok(service.alterarPlano(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deletarPlano(@PathVariable UUID id){
        service.deletarPlano(id);
        return ResponseEntity.ok().build();
    }   
    
    @PatchMapping("beneficio/{id}")
    public ResponseEntity adicionarBeneficio(@PathVariable UUID id,@RequestBody List<UUID> idBeneficios){
        var plano = service.adicionarBeneficio(id, idBeneficios);
        return ResponseEntity.ok(plano);
    }
}