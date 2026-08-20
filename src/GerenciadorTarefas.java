import java.util.ArrayList;

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
            for (Tarefa tarefa : tarefas) {
                System.out.println("ID: " + tarefa.getId() + "| Descrição: " + tarefa.getDescricao() + "| Concluida: " + tarefa.isConcluida());
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
}
