package com.denteconvenio.planoservice.domain.cobertura;

import java.util.List;
import java.util.UUID;

import com.denteconvenio.planoservice.domain.beneficio.Beneficio;
import com.denteconvenio.planoservice.domain.cobertura.HorarioFuncionamento.HorarioFuncionamento;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coberturas")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Cobertura {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    private UUID idConsultorio;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Beneficio> beneficios;

    @OneToMany(mappedBy = "cobertura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioFuncionamento> horarioFuncionamento;

    public Cobertura(UUID idConsultorio, List<Beneficio> beneficios) {
        this.idConsultorio = idConsultorio;
        this.beneficios = beneficios;
    }

   
}
