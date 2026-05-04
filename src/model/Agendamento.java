package model;

import java.time.LocalDateTime;

public class Agendamento {
    private Long id;
    private Cliente cliente;
    private Profissional profissional;
    private LocalDateTime dataHora;
    private String status;

    public Agendamento(Long id, Cliente cliente, Profissional profissional, LocalDateTime dataHora) {
        this.id = id;
        this.cliente = cliente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.status = "AGENDADO";
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void cancelar() {
        this.status = "CANCELADO";
    }
}