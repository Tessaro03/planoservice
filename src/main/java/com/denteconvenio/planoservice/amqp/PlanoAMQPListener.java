package com.denteconvenio.planoservice.amqp;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denteconvenio.planoservice.domain.cobertura.CoberturaRequestDTO;
import com.denteconvenio.planoservice.service.CoberturaService;

@Service
public class PlanoAMQPListener {

    @Autowired
    private CoberturaService coberturaService;

    @RabbitListener(queues = "consultorio.criado") 
	public void receiveMessage(UUID idConsultorio) { 
        coberturaService.criarCobertura(new CoberturaRequestDTO(idConsultorio, null));
    } 

}
