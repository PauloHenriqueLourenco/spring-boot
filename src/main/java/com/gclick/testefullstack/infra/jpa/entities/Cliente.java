package com.gclick.testefullstack.infra.jpa.entities;

import com.gclick.testefullstack.enums.StatusCliente;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"emails"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inscricao;
    private String nome;
    private String apelido;
    private String foto;

    @Enumerated(EnumType.STRING)
    private StatusCliente status;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = {CascadeType.ALL})
    private List<Email> emails;

}
