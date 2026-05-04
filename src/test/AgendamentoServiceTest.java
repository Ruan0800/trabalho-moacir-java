package test;

import model.Agendamento;
import model.Cliente;
import model.Profissional;
import service.AgendamentoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AgendamentoServiceTest {

    private AgendamentoService service;

    @BeforeEach
    public void setUp() {
        // Inicializa um novo serviço "limpo" antes de cada teste
        service = new AgendamentoService();
    }

    @Test
    public void deveAgendarComSucesso() {
        Cliente cliente = new Cliente(1L, "João");
        Profissional profissional = new Profissional(1L, "Maria");
        LocalDateTime dataFutura = LocalDateTime.now().plusDays(1);
        
        Agendamento agendamento = new Agendamento(1L, cliente, profissional, dataFutura);
        
        // Verifica se o método não lança nenhuma exceção
        assertDoesNotThrow(() -> {
            service.agendar(agendamento);
        });
    }

    @Test
    public void naoDeveAgendarNoPassado() {
        Cliente cliente = new Cliente(2L, "Pedro");
        Profissional profissional = new Profissional(2L, "Ana");
        LocalDateTime dataPassada = LocalDateTime.now().minusDays(1);
        
        Agendamento agendamento = new Agendamento(2L, cliente, profissional, dataPassada);
        
        // Verifica se a exceção correta foi lançada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.agendar(agendamento);
        });
        
        assertEquals("Não pode agendar no passado", exception.getMessage());
    }

    @Test
    public void naoDevePermitirConflitoDeHorario() {
        Cliente cliente1 = new Cliente(3L, "Carlos");
        Cliente cliente2 = new Cliente(4L, "Marcos");
        Profissional profissional = new Profissional(3L, "Dr. Roberto"); 
        LocalDateTime horario = LocalDateTime.now().plusHours(2);
        
        Agendamento agendamento1 = new Agendamento(3L, cliente1, profissional, horario);
        Agendamento agendamento2 = new Agendamento(4L, cliente2, profissional, horario);
        
        // O primeiro agendamento deve passar
        service.agendar(agendamento1);
        
        // O segundo deve dar erro
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.agendar(agendamento2);
        });
        
        assertEquals("Horário já ocupado", exception.getMessage());
    }
}
