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
            impressao(copia);
        }
    }

    public void listarAbertas(){
        ArrayList<Tarefa> abertas = new ArrayList<>();
        for (Tarefa tarefa : tarefas) {
            if (!tarefa.isConcluida()){
                abertas.add(tarefa);
            }
        }
        if (abertas.isEmpty()){
            System.out.println("Você não tem tarefas abertas!");
        } else  {
            abertas.sort(Comparator.comparing(Tarefa::getPrioridade).reversed());
            impressao(abertas);
        }
    }

    public void listarConcluidas() {
        ArrayList<Tarefa> concluidas = new ArrayList<>();
        for (Tarefa tarefa : tarefas) {
            if (tarefa.isConcluida()) {
                concluidas.add(tarefa);
            }
        }
        if (concluidas.isEmpty()) {
            System.out.println("Você não tem tarefas concluidas!");
        } else  {
            impressao(concluidas);
        }
    }

    private void impressao(ArrayList<Tarefa> copia) {
        for (Tarefa tarefa : copia) {
            System.out.println("ID: " + tarefa.getId() + "| Descrição: " + tarefa.getDescricao() + "| Concluida: " + tarefa.isConcluida() + "| Prioridade: " + tarefa.getPrioridade());
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
