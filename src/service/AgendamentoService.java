package service;

import java.time.LocalDateTime;
import model.Agendamento;           
import repository.AgendamentoRepository;

public class AgendamentoService {

   private AgendamentoRepository repository = new AgendamentoRepository();

    public void agendar(Agendamento novo) {

        if (novo.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não pode agendar no passado");
        }

        for (Agendamento ag : repository.listar()) {
            if (ag.getProfissional().equals(novo.getProfissional()) &&
                ag.getDataHora().equals(novo.getDataHora())) {
                throw new RuntimeException("Horário já ocupado");
            }
        }

        repository.salvar(novo);
    }
}