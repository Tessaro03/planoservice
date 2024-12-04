package com.denteconvenio.planoservice.domain.plano;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.denteconvenio.planoservice.domain.beneficio.Beneficio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="planos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Plano {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Double valor;
    private String titulo;
    private String descricao;
    private TipoPlano tipo;
    private Double desconto;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Beneficio> beneficios;

    public Plano(Double valor, String titulo, String descricao, TipoPlano tipo, Double desconto) {
        this.valor = valor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.beneficios = new ArrayList<>();
        if (this.tipo == TipoPlano.Empresa) {
            this.desconto = Double.valueOf(String.format("%.2f",desconto / 100));
        }
    }

}
