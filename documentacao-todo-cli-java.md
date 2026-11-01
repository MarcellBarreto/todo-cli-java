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
- Reabrir tarefa concluída
- Prioridade da tarefa (BAIXA, MEDIA, ALTA), com listagem ordenada por prioridade

## Fora de escopo (por enquanto)

- Persistência em arquivo ou banco de dados
- Interface web
- Categorias
- Data de vencimento
- Visão semanal / calendário
- Sessão separada para tarefas concluídas

## Estrutura de classes

- **`Tarefa`** — atributos: `id` (int), `descricao` (String), `concluida` (boolean), `prioridade` (`Prioridade`)
- **`Prioridade`** — `enum` com `BAIXA`, `MEDIA` e `ALTA`, em arquivo próprio, pois `Main` e `Tarefa` o utilizam
- **`GerenciadorTarefas`** — encapsula a `ArrayList<Tarefa>` e os métodos de adicionar, remover, marcar como concluída, reabrir, editar descrição, editar prioridade e listar
- **`Main`** — apenas o loop de menu, a leitura de input via `Scanner` e a conversão do número digitado para `Prioridade`

## Decisões de design (Dia 3)

**Por que a tarefa tem `id`:**
Sem um identificador próprio, operações como "marcar como concluída" ou "remover" dependeriam da posição no array (frágil, muda a cada remoção) ou do texto da descrição (duas tarefas podem ter o mesmo texto). Um `int id` gerado automaticamente pelo `GerenciadorTarefas` resolve isso.

**Onde entra o `Scanner`:**
Só na classe `Main`. `GerenciadorTarefas` não deve saber que existe um terminal — ele recebe dados já lidos (`String descricao`, `int id`) e devolve resultados. Essa separação de responsabilidade já foi praticada no exercício de aquecimento (`GerenciadorLivros`).

**Por que `marcarConcluida` e `remover` retornam `boolean`:**
Preparação para o próximo passo: tratar o caso de um `id` que não existe na lista. O retorno booleano permite que a `Main` decida a mensagem de erro apropriada, sem misturar essa lógica dentro do `GerenciadorTarefas`.

**Por que `setDescricao` e não `editarDescricao`/`alterarDescricao` (Dia 4):**
`Tarefa` já tem `concluir()`, um método verbo sem prefixo `set`, então `setDescricao` quebra a simetria interna da classe. Ainda assim, optou-se por manter o padrão getter/setter tradicional do Java em vez de um nome estilo verbo, priorizando a convenção da linguagem — reconhecível por qualquer leitor de Java — sobre a consistência interna com `concluir()`. Trade-off aceito conscientemente, não uma inconsistência despercebida.

**Por que a prioridade é um `enum` (pós-MVP):**
Os valores são fixos e conhecidos, então o compilador rejeita qualquer valor fora de `BAIXA`, `MEDIA` e `ALTA`. Por isso `setPrioridade` não precisa de validação, diferente de `setDescricao`.

**Por que `setPrioridade` (pós-MVP):**
Pela mesma razão de `setDescricao`: o método só atribui um valor ao campo.

**Por que a prioridade nasce `MEDIA`:**
Simplifica o cadastro, que não pede prioridade. Quem quiser outra prioridade usa a opção de edição.

**Como o submenu lê a prioridade (pós-MVP):**
A `Main` pede um número de 1 a 3 e converte para o `enum` com `switch`. Um laço repete a pergunta até receber um valor válido, e o `try/catch` fica dentro do laço, para que uma entrada não numérica não faça o usuário voltar ao menu principal. O `GerenciadorTarefas` recebe só o `Prioridade` já convertido.

**Por que `listar()` ordena uma cópia (pós-MVP):**
A ordem de cadastro fica preservada na lista guardada, para uso futuro (por exemplo, um filtro por data de criação). A ordenação por prioridade (`ALTA` primeiro) acontece numa cópia criada dentro do `listar()`.

## Esqueleto de classes

```java
public enum Prioridade {
    BAIXA, MEDIA, ALTA
}
```

```java
public class Tarefa {
    private int id;
    private String descricao;
    private boolean concluida;
    private Prioridade prioridade;

    public Tarefa(int id, String descricao) {
    }

    // getters e setters

    public void setDescricao(String novaDescricao) {
    }

    public void setPrioridade(Prioridade novaPrioridade) {
    }

    public Prioridade getPrioridade() {
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

    public boolean reabrir(int id) {
    }

    public boolean editarDescricao(int id, String novaDescricao) {
    }

    public boolean editarPrioridade(int id, Prioridade novaPrioridade) {
    }
}
```

## Progresso até aqui

- **Dia 1:** Aquecimento com classes, `ArrayList`, for-each e separação de responsabilidade em classe própria. Exercício prático: `PraticaCadastroLivros` (cadastro/busca de livros), testado e versionado no GitHub.
- **Dia 2:** Prática de `try/catch` com `InputMismatchException` e `IndexOutOfBoundsException`.
- **Dia 3:** Definição formal do escopo da MVP e decisões de design das classes principais.
- **Dia 4:** Implementação da edição de descrição de tarefa (`setDescricao` na `Tarefa`, `editarDescricao` no `GerenciadorTarefas`, novo case no menu da `Main`), com validação rejeitando descrição vazia/em branco via `IllegalArgumentException`. Escopo do doc atualizado e feature commitada e enviada ao GitHub (`99b07e9`).
- **Pós-MVP — reabertura:** `reabrir()` na `Tarefa` e `reabrir(id)` no `GerenciadorTarefas`, para desfazer a conclusão. A reabertura entrou como opção 6 do menu, e "Sair" passou para a opção 7 (`80a5f79`).
- **Pós-MVP — constantes do menu:** a opção de sair e o total de opções viraram as constantes `SAIR` e `TOTAL_OPCOES`, para que a renumeração do menu não quebre o `while` nem a mensagem do `default` (`8e0652e`).
- **Pós-MVP — prioridade:** campo `prioridade` na `Tarefa` (padrão `MEDIA`), `editarPrioridade` no `GerenciadorTarefas`, submenu de 1 a 3 na opção 6 do menu e ordenação por prioridade no `listar()`, com `ALTA` primeiro (`64d9898`).

## Regra de disciplina

Não ficar mais de 2 dias seguidos sem tocar no projeto — regra criada para evitar o padrão de abandono que já aconteceu antes (interrupção de 5–6 meses após uma sequência de commits diários que quebrou).

## Visão de longo prazo (pós-MVP)

O projeto final imaginado é algo no estilo Kanban: aplicação web, visão semanal das tarefas, persistência de dados, e uma seção separada para itens concluídos. Nada disso entra agora — a prioridade é terminar a v1 em Java puro no terminal antes de expandir o escopo.
