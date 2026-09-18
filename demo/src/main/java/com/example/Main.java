package com.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnimalSilvestreDAO dao = new AnimalSilvestreDAO();
        dao.criarTabela();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== CRUD de Animais Silvestres ===");
            System.out.println("1 - Cadastrar animal");
            System.out.println("2 - Listar animais");
            System.out.println("3 - Buscar animal por nome");
            System.out.println("4 - Atualizar animal");
            System.out.println("5 - Excluir animal");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Peso médio: ");
                    double peso = Double.parseDouble(scanner.nextLine());
                    System.out.print("Bioma: ");
                    String bioma = scanner.nextLine();
                    System.out.print("Está em risco de extinção? (true/false): ");
                    boolean risco = Boolean.parseBoolean(scanner.nextLine());

                    AnimalSilvestre animal = new AnimalSilvestre(nome, peso, bioma, risco);
                    dao.salvar(animal);
                    System.out.println("Animal cadastrado com sucesso!");
                }
                case 2 -> {
                    List<AnimalSilvestre> animais = dao.listarTodos();
                    if (animais.isEmpty()) {
                        System.out.println("Nenhum animal cadastrado.");
                    } else {
                        for (AnimalSilvestre animal : animais) {
                            System.out.println("Nome: " + animal.getNome()
                                    + ", Peso médio: " + animal.getPesoMedio()
                                    + ", Bioma: " + animal.getBioma()
                                    + ", Em risco: " + animal.isEmRiscoExtincao());
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Nome do animal: ");
                    String nome = scanner.nextLine();
                    AnimalSilvestre animal = dao.buscarPorNome(nome);
                    if (animal == null) {
                        System.out.println("Animal não encontrado.");
                    } else {
                        System.out.println("Nome: " + animal.getNome()
                                + ", Peso médio: " + animal.getPesoMedio()
                                + ", Bioma: " + animal.getBioma()
                                + ", Em risco: " + animal.isEmRiscoExtincao());
                    }
                }
                case 4 -> {
                    System.out.print("Nome do animal para atualizar: ");
                    String nome = scanner.nextLine();
                    AnimalSilvestre animal = dao.buscarPorNome(nome);
                    if (animal == null) {
                        System.out.println("Animal não encontrado.");
                        break;
                    }

                    System.out.print("Novo peso médio: ");
                    animal.setPesoMedio(Double.parseDouble(scanner.nextLine()));
                    System.out.print("Novo bioma: ");
                    animal.setBioma(scanner.nextLine());
                    System.out.print("Está em risco de extinção? (true/false): ");
                    animal.setEmRiscoExtincao(Boolean.parseBoolean(scanner.nextLine()));

                    dao.atualizar(animal);
                    System.out.println("Animal atualizado com sucesso!");
                }
                case 5 -> {
                    System.out.print("Nome do animal para excluir: ");
                    String nome = scanner.nextLine();
                    dao.excluir(nome);
                    System.out.println("Animal excluído com sucesso!");
                }
                case 0 -> {
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}