package com.denteconvenio.planoservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denteconvenio.planoservice.domain.beneficio.BeneficioRequestDTO;
import com.denteconvenio.planoservice.service.BeneficioService;



@RestController
@RequestMapping("/beneficio")
public class BeneficioController {

    @Autowired
    private BeneficioService service;

    @GetMapping
    public ResponseEntity verBeneficios(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.verBeneficios(pageable));
    }
    
    @PostMapping
    public ResponseEntity criarBeneficio(@RequestBody BeneficioRequestDTO dto) {
        return ResponseEntity.ok(service.criarBeneficio(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity alterarBeneficio(@PathVariable UUID id,@RequestBody BeneficioRequestDTO dto) {
        return ResponseEntity.ok(service.alterarBeneficio(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deletarBeneficio(@PathVariable UUID id) {
        service.deletarBeneficio(id);
        return ResponseEntity.ok().build();
    }
}
