import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int SAIR = 7;
        final int TOTAL_OPCOES = 7;

        System.out.println("Bem vindo ao To-Do list!!!");
        int escolha = 0;

        GerenciadorTarefas gerenciadorT = new GerenciadorTarefas();
        while (escolha != SAIR) {
            try {
                System.out.println("\n======= MENU =======");
                System.out.println("1 - Cadastrar tarefa");
                System.out.println("2 - Listar tarefas");
                System.out.println("3 - Remover tarefa");
                System.out.println("4 - Marcar tarefa como concluida");
                System.out.println("5 - Editar tarefa");
                System.out.println("6 - Reabrir tarefa concluida");
                System.out.println(SAIR + " - Sair");
                System.out.println("====================");
                escolha = sc.nextInt();

                switch (escolha) {
                    case 1:
                        System.out.println("Digite a descrição da tarefa: ");
                        sc.nextLine();
                        String descricao = sc.nextLine();
                        gerenciadorT.adicionar(descricao);
                        break;
                    case 2:
                        int escolhaEstado = 0;
                        while (escolhaEstado != 1 && escolhaEstado != 2 && escolhaEstado != 3) {
                            try {
                                System.out.println("Qual o estado da tarefa? ");
                                System.out.println("1 - Todas | 2 - Abertas | 3 - Concluidas");
                                System.out.println("====================");
                                escolhaEstado =  sc.nextInt();

                                switch (escolhaEstado) {
                                    case 1:
                                        gerenciadorT.listar();
                                        break;
                                    case 2:
                                        gerenciadorT.listarAbertas();
                                        break;
                                    case 3:
                                        gerenciadorT.listarConcluidas();
                                        break;
                                    default:
                                        System.out.println("ERRO!!!");
                                        System.out.println("DIGITE UMA OPCAO VALIDA (1 A 3).");
                                        break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("ERRO!!! Você digitou algo que não é um número.");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Digite o ID da tarefa que deseja remover: ");
                        if (gerenciadorT.remover(sc.nextInt())) {
                            System.out.println("Tarefa removida com sucesso!");
                        } else {
                            System.out.println("Tarefa não encontrada!");
                        }
                        break;
                    case 4:
                        System.out.println("Digite o ID da tarefa concluida: ");
                        if (gerenciadorT.marcarConcluida(sc.nextInt())) {
                            System.out.println("Tarefa concluida com sucesso!");
                        } else {
                            System.out.println("Tarefa não encontrada!");
                        }
                        break;
                    case 5:
                        System.out.println("Digite o ID da tarefa que deseja editar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Prioridade prioridade = Prioridade.MEDIA;
                        int escolhaEdicao = 0;
                        int escolhaPrioridade = 0;

                        while (escolhaEdicao != 1 && escolhaEdicao != 2){
                            try {
                                System.out.println("\nDigite o numero da edição desejada: ");
                                System.out.println("1 - Descrição | 2 - Prioridade");
                                System.out.println("====================");
                                escolhaEdicao = sc.nextInt();
                                sc.nextLine();
                                switch (escolhaEdicao) {
                                    case 1:
                                        System.out.println("Digite a nova descrição:");
                                        String novaDescricao = sc.nextLine();
                                        if (gerenciadorT.editarDescricao(id, novaDescricao)) {
                                            System.out.println("Tarefa editada com sucesso!");
                                        } else {
                                            System.out.println("Tarefa não encontrada!");
                                        }
                                        break;
                                    case 2:
                                        while (escolhaPrioridade != 1 && escolhaPrioridade != 2 && escolhaPrioridade != 3) {
                                            System.out.println("\nDigite o numero da nova prioridade: ");
                                            System.out.println("1 - Baixa | 2 - Media | 3 - Alta");
                                            System.out.println("====================");
                                            try {
                                                escolhaPrioridade = sc.nextInt();
                                                switch (escolhaPrioridade) {
                                                    case 1:
                                                        prioridade = Prioridade.BAIXA;
                                                        break;
                                                    case 2:
                                                        prioridade = Prioridade.MEDIA;
                                                        break;
                                                    case 3:
                                                        prioridade = Prioridade.ALTA;
                                                        break;
                                                    default:
                                                        System.out.println("ERRO!!!");
                                                        System.out.println("DIGITE UMA OPCAO VALIDA (1 A 3).");
                                                        break;
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("ERRO!!! Você digitou algo que não é um número.");
                                                sc.nextLine();
                                            }
                                        }
                                        if (gerenciadorT.editarPrioridade(id, prioridade)) {
                                            System.out.println("Prioridade editada com sucesso!");
                                        } else {
                                            System.out.println("Tarefa não encontrada!");
                                        }
                                        break;
                                    default:
                                        System.out.println("ERRO!!!");
                                        System.out.println("DIGITE UMA OPCAO VALIDA (1 ou 2).");
                                        break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("ERRO!!! Você digitou algo que não é um número.");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Digite o ID da tarefa que será reaberta: ");
                        if (gerenciadorT.reabrir(sc.nextInt())) {
                            System.out.println("Tarefa reaberta com sucesso!");
                        } else {
                            System.out.println("Tarefa não encontrada!");
                        }
                        break;
                    case SAIR:
                        System.out.println("======= PROGRAMA ENCERRADO =======");
                        break;

                    default:
                        System.out.println("ERRO!!!");
                        System.out.println("DIGITE UMA OPCAO VALIDA (1 A " + TOTAL_OPCOES + ").");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERRO!!! Você digitou algo que não é um número.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
