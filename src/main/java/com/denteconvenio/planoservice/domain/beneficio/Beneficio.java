package com.denteconvenio.planoservice.domain.beneficio;


import java.util.List;
import java.util.UUID;

import com.denteconvenio.planoservice.domain.cobertura.Cobertura;
import com.denteconvenio.planoservice.domain.plano.Plano;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "beneficios")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Beneficio {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String titulo;
    private String descricao;

    @ManyToMany(mappedBy = "beneficios")
    private List<Plano> plano;

    @ManyToMany(mappedBy= "beneficios")
    private List<Cobertura> coberturas;


    public Beneficio(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

}
