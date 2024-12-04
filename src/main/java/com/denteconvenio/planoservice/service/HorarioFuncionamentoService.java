package com.denteconvenio.planoservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamento;
import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamentoRequestDTO;
import com.denteconvenio.planoservice.infra.security.TokenService;
import com.denteconvenio.planoservice.repository.CoberturaRepository;
import com.denteconvenio.planoservice.repository.HorarioFuncionamentoRepository;
import com.denteconvenio.planoservice.validations.HoraAtendimento.ValidationHorarioFuncionamento;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class HorarioFuncionamentoService {
    
    @Autowired
    private CoberturaRepository coberturaRepository;

    @Autowired
    private HorarioFuncionamentoRepository repository;

    @Autowired
    private ValidationHorarioFuncionamento validacao;
    
    @Autowired 
    private TokenService tokenService;

    public void adicionarHorarioFuncionamento(HttpServletRequest request, List<HorarioFuncionamentoRequestDTO> dtos) {
        var consultorioToken = tokenService.extrairInformacoes(request);
        var cobertura = coberturaRepository.findByIdConsultorio(consultorioToken.id());
        for (HorarioFuncionamentoRequestDTO dto : dtos) {
            validacao.validarPost(dto);
            if (!repository.existsByDiaSemanaAndCoberturaId(dto.diaSemana(), cobertura.getId())) {
                var horarioFuncionamento = new HorarioFuncionamento((dto.diaSemana()), dto.horarioAbertura(), dto.horarioFechamento(), cobertura);
                cobertura.getHorarioFuncionamento().add(horarioFuncionamento);
                repository.save(horarioFuncionamento);
            }
        }
    }

    public void alterarHorarioFuncionamento(HttpServletRequest request, HorarioFuncionamentoRequestDTO dto) {
        var consultorioToken = tokenService.extrairInformacoes(request);
        var cobertura = coberturaRepository.findByIdConsultorio(consultorioToken.id());
        var horario = repository.findByDiaSemanaAndCoberturaId(dto.diaSemana(), cobertura.getId());
        
        if (horario.isPresent()) {
            validacao.validarPatch(horario.get(), dto);
            if (dto.horarioAbertura() != null) {
                horario.get().setHorarioAbertura(dto.horarioAbertura());
            }
            if (dto.horarioFechamento() != null) {
                horario.get().setHorarioFechamento(dto.horarioFechamento());
            }
        }
        repository.save(horario.get());

    }

}
