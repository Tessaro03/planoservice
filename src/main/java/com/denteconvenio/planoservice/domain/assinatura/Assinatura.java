package com.denteconvenio.planoservice.domain.assinatura;

import java.time.LocalDateTime;
import java.util.UUID;

import com.denteconvenio.planoservice.domain.plano.Plano;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="assinaturas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Assinatura {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Plano plano;
    private UUID idAssinante;
    private LocalDateTime validade;
    private Boolean ativo;

    public Assinatura(Plano plano, UUID idAssinante, Long validade) {
        this.plano = plano;
        this.idAssinante = idAssinante;
        this.validade = LocalDateTime.now().plusMonths(validade);
        this.ativo = true;
    }

}
