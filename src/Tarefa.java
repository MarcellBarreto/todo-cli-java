public class Tarefa {

    private int id;
    private String descricao;
    private boolean concluida;

    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = false;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String novaDescricao) {
        if (novaDescricao.trim().isEmpty()){
            throw new IllegalArgumentException("ERRO!!! Digite a nova descrição");
        }else {
            this.descricao = novaDescricao;
        }
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluir(){
        this.concluida = true;
    }

    public void reabrir(){
        this.concluida = false;
    }

}
