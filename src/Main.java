import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao To-Do list!!!");
        int escolha = 0;


        GerenciadorTarefas gerenciadorT = new GerenciadorTarefas();
        while (escolha != 6) {
            try {
                System.out.println("\n======= MENU =======");
                System.out.println("1 - Cadastrar tarefa");
                System.out.println("2 - Listar tarefas");
                System.out.println("3 - Remover tarefa");
                System.out.println("4 - Marcar tarefa como concluida");
                System.out.println("5 - Editar tarefa");
                System.out.println("6 - Sair");
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
                        gerenciadorT.listar();
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
                        System.out.print("Digite a nova descrição da tarefa: ");
                        String novaDescricao = sc.nextLine();

                        if (gerenciadorT.editarDescricao(id, novaDescricao)) {
                            System.out.println("Tarefa editada com sucesso!");
                        } else {
                            System.out.println("Tarefa não encontrada!");
                        }
                        break;
                    case 6:
                        System.out.println("======= PROGRAMA ENCERRADO =======");
                        break;

                    default:
                        System.out.println("ERRO!!!");
                        System.out.println("DIGITE UMA OPCAO VALIDA (1 A 6).");
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
