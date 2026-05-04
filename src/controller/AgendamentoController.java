package controller;

import java.time.LocalDateTime;
import model.Agendamento;   
import model.Cliente;        
import model.Profissional;  
import service.AgendamentoService; 

public class AgendamentoController {

    private AgendamentoService service = new AgendamentoService();

    public void criarAgendamento() {

        Cliente cliente = new Cliente(1L, "João");
        Profissional profissional = new Profissional(1L, "Maria");

        Agendamento agendamento = new Agendamento(
                1L,
                cliente,
                profissional,
                LocalDateTime.now().plusHours(1)
        );

        service.agendar(agendamento);

        System.out.println("Agendamento criado com sucesso!");
    }
}
