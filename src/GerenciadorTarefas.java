import java.util.ArrayList;
import java.util.Comparator;

public class GerenciadorTarefas {
    private ArrayList<Tarefa> tarefas;
    private int proximoId;

    public GerenciadorTarefas() {
        tarefas = new ArrayList<>();
        proximoId = 1;
    }

    public void adicionar(String descricao) {
        tarefas.add(new Tarefa(proximoId, descricao));
        proximoId++;
    }

    public void listar() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada!");
        } else {
            ArrayList<Tarefa> copia = new ArrayList<>(tarefas);
            copia.sort(Comparator.comparing(Tarefa::getPrioridade).reversed());
            for (Tarefa tarefa : copia) {
                System.out.println("ID: " + tarefa.getId() + "| Descrição: " + tarefa.getDescricao() + "| Concluida: " + tarefa.isConcluida() + "| Prioridade: " + tarefa.getPrioridade());
            }
        }
    }

    public boolean remover(int id) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefas.remove(tarefa);
                return true;
            }
        }
        return false;
    }

    public boolean marcarConcluida(int id) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.concluir();
                return true;
            }
        }
        return false;
    }

    public boolean reabrir(int id) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.reabrir();
                return true;
            }
        }
        return false;
    }

    public boolean editarDescricao(int id, String novaDescricao) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }

    public boolean editarPrioridade(int id, Prioridade novaPrioridade) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setPrioridade(novaPrioridade);
                return true;
            }
        }
        return false;
    }
}
