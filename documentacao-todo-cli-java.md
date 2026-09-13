# Documentação — Projeto To-do CLI em Java

## Contexto

Primeiro projeto pessoal completo em Java, feito durante o aprendizado pelo curso do Nélio Alves (DevSuperior), atualmente na Section 10 (comportamento de memória, arrays e listas). Nunca foi finalizado um projeto do início ao fim antes — esse é o foco principal aqui: **terminar**, não só começar.

## Objetivo do projeto

Construir uma aplicação de linha de comando (CLI) para gerenciar tarefas do dia a dia: adicionar, visualizar, concluir e remover.

Esse é o ponto de partida de uma visão maior (ver seção "Visão de longo prazo"), mas a v1 é deliberadamente pequena para garantir a conclusão.

## Escopo da MVP (v1)

- Adicionar tarefa (descrição)
- Listar tarefas
- Marcar tarefa como concluída
- Remover tarefa
- Interação via terminal usando `Scanner`
- Dados mantidos em memória (`ArrayList`), sem persistência
- Edição de tarefas já criadas

## Fora de escopo (por enquanto)

- Reabrir a tarefa
- Persistência em arquivo ou banco de dados
- Interface web
- Categorias
- Data de vencimento
- Prioridade
- Visão semanal / calendário
- Sessão separada para tarefas concluídas

## Estrutura de classes

- **`Tarefa`** — atributos: `id` (int), `descricao` (String), `concluida` (boolean)
- **`GerenciadorTarefas`** — encapsula a `ArrayList<Tarefa>` e os métodos de adicionar, remover, marcar como concluída e listar
- **`Main`** — apenas o loop de menu e leitura de input via `Scanner`

## Decisões de design (Dia 3)

**Por que a tarefa tem `id`:**
Sem um identificador próprio, operações como "marcar como concluída" ou "remover" dependeriam da posição no array (frágil, muda a cada remoção) ou do texto da descrição (duas tarefas podem ter o mesmo texto). Um `int id` gerado automaticamente pelo `GerenciadorTarefas` resolve isso.

**Onde entra o `Scanner`:**
Só na classe `Main`. `GerenciadorTarefas` não deve saber que existe um terminal — ele recebe dados já lidos (`String descricao`, `int id`) e devolve resultados. Essa separação de responsabilidade já foi praticada no exercício de aquecimento (`GerenciadorLivros`).

**Por que `marcarConcluida` e `remover` retornam `boolean`:**
Preparação para o próximo passo: tratar o caso de um `id` que não existe na lista. O retorno booleano permite que a `Main` decida a mensagem de erro apropriada, sem misturar essa lógica dentro do `GerenciadorTarefas`.

**Por que `setDescricao` e não `editarDescricao`/`alterarDescricao` (Dia 4):**
`Tarefa` já tem `concluir()`, um método verbo sem prefixo `set`, então `setDescricao` quebra a simetria interna da classe. Ainda assim, optou-se por manter o padrão getter/setter tradicional do Java em vez de um nome estilo verbo, priorizando a convenção da linguagem — reconhecível por qualquer leitor de Java — sobre a consistência interna com `concluir()`. Trade-off aceito conscientemente, não uma inconsistência despercebida.

## Esqueleto de classes

```java
public class Tarefa {
    private int id;
    private String descricao;
    private boolean concluida;

    public Tarefa(int id, String descricao) {
    }

    // getters e setters

    public void setDescricao(String novaDescricao) {
    }
}
```

```java
public class GerenciadorTarefas {   
    private List<Tarefa> tarefas;
    private int proximoId;

    public GerenciadorTarefas() {
    }

    public void adicionar(String descricao) {
    }

    public void listar() {
    }

    public boolean marcarConcluida(int id) {
    }

    public boolean remover(int id) {
    }

    public boolean editarDescricao(int id, String novaDescricao) {
    }
}
```

## Progresso até aqui

- **Dia 1:** Aquecimento com classes, `ArrayList`, for-each e separação de responsabilidade em classe própria. Exercício prático: `PraticaCadastroLivros` (cadastro/busca de livros), testado e versionado no GitHub.
- **Dia 2:** Prática de `try/catch` com `InputMismatchException` e `IndexOutOfBoundsException`.
- **Dia 3:** Definição formal do escopo da MVP e decisões de design das classes principais.
- **Dia 4:** Implementação da edição de descrição de tarefa (`setDescricao` na `Tarefa`, `editarDescricao` no `GerenciadorTarefas`, novo case no menu da `Main`), com validação rejeitando descrição vazia/em branco via `IllegalArgumentException`. Escopo do doc atualizado e feature commitada e enviada ao GitHub (`99b07e9`).

## Regra de disciplina

Não ficar mais de 2 dias seguidos sem tocar no projeto — regra criada para evitar o padrão de abandono que já aconteceu antes (interrupção de 5–6 meses após uma sequência de commits diários que quebrou).

## Visão de longo prazo (pós-MVP)

O projeto final imaginado é algo no estilo Kanban: aplicação web, visão semanal das tarefas, persistência de dados, e uma seção separada para itens concluídos. Nada disso entra agora — a prioridade é terminar a v1 em Java puro no terminal antes de expandir o escopo.
