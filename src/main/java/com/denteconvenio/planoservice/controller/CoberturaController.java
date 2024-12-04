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

import com.denteconvenio.planoservice.domain.cobertura.CoberturaRequestDTO;
import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamentoRequestDTO;
import com.denteconvenio.planoservice.service.CoberturaService;
import com.denteconvenio.planoservice.service.HorarioFuncionamentoService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("cobertura")
public class CoberturaController {
    
    @Autowired
    private CoberturaService service;

    @Autowired
    private HorarioFuncionamentoService horarioFuncionamentoService;


    @GetMapping("/{idConsultorio}")
    public ResponseEntity verCoberturaConsultorio(@PathVariable UUID idConsultorio) {
        return ResponseEntity.ok(service.verCoberturaConsultorio(idConsultorio));
    }
    
    @PostMapping("/beneficio")
    public ResponseEntity adicionarBeneficio(HttpServletRequest request,@RequestBody CoberturaRequestDTO dto) {
        return ResponseEntity.ok(service.adicionarBeneficio(request, dto));
    }

    @DeleteMapping("/beneficio")
    public ResponseEntity removerBeneficio(HttpServletRequest request,@RequestBody CoberturaRequestDTO dto) {
        return ResponseEntity.ok(service.removerBeneficio(request, dto));
    }

    @PostMapping("/atendimento")
    public void adicionarHorarioFuncionamento(HttpServletRequest request,@RequestBody List<HorarioFuncionamentoRequestDTO> dtos) {
        horarioFuncionamentoService.adicionarHorarioFuncionamento(request, dtos);
    }

    @PatchMapping("/atendimento")
    public void alterarHorarioFuncionamento(HttpServletRequest request, @RequestBody HorarioFuncionamentoRequestDTO dtos){
        horarioFuncionamentoService.alterarHorarioFuncionamento(request, dtos);
    }

    @PostMapping("{idConsultorio}/{idBeneficio}")
    public ResponseEntity consultorioCobreBeneficio(@PathVariable UUID idConsultorio,@PathVariable UUID idBeneficio) {
        return ResponseEntity.ok(service.consultorioCobreBeneficio(idConsultorio, idBeneficio));
    }


}
