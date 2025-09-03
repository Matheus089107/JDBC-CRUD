package org.example;

import org.example.dao.ContatoDAO;
import org.example.model.Contato;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner imput = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }
    public static void inicio(){
        boolean sair = false;
        System.out.println("""
                ========================================================
                1. Cadastrar contato: Nome, Telefone, Email, Observação.
                2. Listar todos os contatos cadastrados.
                3. Buscar contato por nome.
                4. Atualizar dados de um contato (telefone, email, observação).
                5. Remover contato.
                6. Sair do sistema.
                ========================================================
                """);
        int opcao = imput.nextInt();
        imput.nextLine();

        switch(opcao){
            case 1:{
                cadastrarContato();
                break;
            }
            case 2: {
                listarContato();
                break;
            }
            case 3:{
                buscarContatoPorNome();
                break;
            }
            case 4: {
                atualizarContato();
            }
            case 6:{
                sair = true;
                break;
            }
        }
        if(!sair){
            inicio();
        }
    }
    public static void cadastrarContato(){
        System.out.println("---cadastro de contato---\n");
        System.out.println("Digite o nome do contato");
        String nome = imput.nextLine();

        System.out.println("Digite o telefone");
        String telefone = imput.nextLine();
        System.out.println("Digite o email do contato");
        String email = imput.nextLine();
        System.out.println("Digite uma observação");
        String observacao = imput.nextLine();

        var contato = new Contato(nome,telefone,email, observacao);
        var dao = new ContatoDAO();

        try{
            dao.inserirContato(contato);
            System.out.println("Contato inserido com sucesso");
        } catch(SQLException e){
            System.out.println("Erro no banco de dados");
            e.printStackTrace();
        }
    }
    public static void listarContato(){
        System.out.println("===CONTATOS===");
        var dao = new ContatoDAO();
        try{
            List<Contato> contatos = dao.listarContatos();
            exibirContatos(contatos);

        }catch (SQLException e) {
            System.out.println("Erro de conexão com o banco");
        }
    }
    public static void buscarContatoPorNome(){
        System.out.println("Digite o nome do contato que deseja procurar");
        String nome = imput.nextLine();

        var dao = new ContatoDAO();
        try{
            List<Contato> contatos = new ArrayList<>();
            exibirContatos(contatos);
        } catch(SQLException e){
            System.out.println("Erro no banco de dados");
            e.printStackTrace();
        }
    }
    public static void exibirContatos(List<Contato> contatos) throws SQLException{
        List <Integer> idContatos = new ArrayList<>();
        for (Contato contato : contatos){
            System.out.println("ID: " + contato.getId());
            System.out.println("NOME: " + contato.getNome());
            System.out.println("TELEFONE: " + contato.getTelefone());
            System.out.println("EMAIL: " + contato.getEmail());
            System.out.println("OBSERVAÇÃO: "+ contato.getObservacao());
        }
    }
    public static void atualizarContato(){
        var dao = new ContatoDAO();
        List <Integer> idContatos = new ArrayList<>();
        try{
            List<Contato> contatos = dao.listarContatos();

            exibirContatos(contatos);
        } catch (SQLException e){
            System.out.println("erro ao conectar no banco de dados");
            e.printStackTrace();
        }
        System.out.println("Digite o id do contato de deseja alterar: ");
        int idContato = imput.nextInt();
        imput.nextLine();

        if(idContatos.contains(idContato)){

        } else {
            System.out.println("Opção invalidade! tente novamente");
            atualizarContato();
        }

    }
}