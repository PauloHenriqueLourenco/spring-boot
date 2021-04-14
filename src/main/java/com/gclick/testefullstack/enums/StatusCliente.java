package com.gclick.testefullstack.enums;

import lombok.Getter;

import java.util.stream.Stream;

public enum StatusCliente {

    DESATIVADO(0, "DESATIVADO"),
    ATIVADO(1, "ATIVADO"),
    SUSPENSO(2, "SUSPENSO");

    @Getter
    private final int id;

    @Getter
    private final String descricao;

    StatusCliente(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static StatusCliente getById(int id) {
        return Stream.of(StatusCliente.values())
                .filter(statusCliente -> statusCliente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static StatusCliente getByDescricao(String descricao) {
        return Stream.of(StatusCliente.values())
                .filter(statusCliente -> statusCliente.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }
}
