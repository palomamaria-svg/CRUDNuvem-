package br.com.crudnuvem;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        try (Scanner entrada = new Scanner(System.in)) {
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n===== CRUD DE PRODUTOS =====");
                System.out.println("1 - Cadastrar produto");
                System.out.println("2 - Listar produtos");
                System.out.println("3 - Atualizar produto");
                System.out.println("4 - Excluir produto");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.parseInt(entrada.nextLine());

                    switch (opcao) {
                        case 1:
                            System.out.print("Nome: ");
                            String nome = entrada.nextLine();
                            System.out.print("Descrição: ");
                            String descricao = entrada.nextLine();
                            System.out.print("Preço: ");
                            double preco = Double.parseDouble(entrada.nextLine().replace(",", "."));
                            System.out.print("Quantidade: ");
                            int quantidade = Integer.parseInt(entrada.nextLine());
                            produtoDAO.inserir(new Produto(nome, descricao, preco, quantidade));
                            System.out.println("Produto cadastrado com sucesso!");
                            break;

                        case 2:
                            produtoDAO.listar();
                            break;

                        case 3:
                            System.out.print("ID do produto: ");
                            int idAtualizar = Integer.parseInt(entrada.nextLine());
                            System.out.print("Novo nome: ");
                            String novoNome = entrada.nextLine();
                            System.out.print("Nova descrição: ");
                            String novaDescricao = entrada.nextLine();
                            System.out.print("Novo preço: ");
                            double novoPreco = Double.parseDouble(entrada.nextLine().replace(",", "."));
                            System.out.print("Nova quantidade: ");
                            int novaQuantidade = Integer.parseInt(entrada.nextLine());
                            produtoDAO.atualizar(new Produto(idAtualizar, novoNome, novaDescricao, novoPreco, novaQuantidade));
                            System.out.println("Atualização realizada.");
                            break;

                        case 4:
                            System.out.print("ID do produto: ");
                            int idExcluir = Integer.parseInt(entrada.nextLine());
                            produtoDAO.excluir(idExcluir);
                            System.out.println("Exclusão realizada.");
                            break;

                        case 0:
                            System.out.println("Programa encerrado.");
                            break;

                        default:
                            System.out.println("Opção inválida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido.");
                } catch (SQLException e) {
                    System.out.println("Erro no banco de dados: " + e.getMessage());
                }
            }
        }
    }
}
