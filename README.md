# To-Do CLI em Java

Aplicação de linha de comando para gerenciar tarefas: cadastrar, listar, concluir, reabrir, editar e remover, com prioridade por tarefa.

Projeto de estudo desenvolvido durante o curso de Java do DevSuperior (Nélio Alves). Foi o primeiro projeto pessoal concluído do início ao fim e continua evoluindo junto com o aprendizado.

## Funcionalidades

- Cadastrar tarefa com descrição
- Listar tarefas, com as de prioridade `ALTA` primeiro
- Marcar tarefa como concluída e reabri-la
- Editar a descrição (descrição vazia ou só com espaços é rejeitada)
- Editar a prioridade: `BAIXA`, `MEDIA` ou `ALTA` (toda tarefa nova começa como `MEDIA`)
- Remover tarefa

## Pré-requisitos

- Java 21 (versão em que o projeto foi testado)

## Como executar

Na raiz do projeto:

```bash
javac -d bin src/*.java
java -cp bin Main
```

## Estrutura

| Arquivo | Responsabilidade |
| --- | --- |
| `src/Main.java` | Menu e leitura da entrada do usuário com `Scanner` |
| `src/GerenciadorTarefas.java` | Regras para manipular a lista de tarefas |
| `src/Tarefa.java` | Entidade `Tarefa` e suas validações |
| `src/Prioridade.java` | `enum` com os níveis de prioridade |

## Decisões de design

- **Separação de responsabilidades:** só a `Main` lê do terminal. O `GerenciadorTarefas` recebe dados já prontos e devolve resultados.
- **Validação onde a regra vive:** a descrição vazia é rejeitada no `setDescricao` da `Tarefa`, então nenhum chamador consegue contorná-la.
- **Prioridade como `enum`:** o compilador só aceita os três valores definidos, sem necessidade de validação extra.
- **Ordenação sem alterar a lista:** o `listar()` ordena uma cópia, preservando a ordem de cadastro.

## Limitações

- As tarefas ficam só em memória: ao fechar o programa, elas são perdidas.

## Documentação

O arquivo `documentacao-todo-cli-java.md` registra o escopo, as decisões de design e o progresso do projeto.
