package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import jsonmanager.CompiladorJsonCliente;

public class MenuEmpresa {

    CompiladorJsonCliente compilador = new CompiladorJsonCliente();
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    //tem um jeito melhor de fazer isso
    //TODO - limpar essas variáveis
    String razaoSocial;
    String cnpj;
    String email;
    String senha;
    String descricao;
    String ramo;
    String input;
    String nome;
    String estado;
    int id;
    double faixaSalarial;
    String competencia;
    String output;
    String ultimoOutput;
    String ultimaOperacao;

    public String handle(String token) throws IOException {
        System.out.println("Handle iniciado - Candidato");
        System.out.println("""
                            Selecione uma operacao.
                           --- Empresa ---
                            1 - Cadastrar
                            2 - Login
                            3 - Visualizar
                            4 - Apagar
                            5 - Atualizar
                            6 - Logout
                           --- Vagas ---
                           7 - Inserir
                           8 - Visualizar
                           9 - Atualizar
                           10 - Apagar
                           11 - Listar""");
        input = stdIn.readLine();
        if (Integer.parseInt(input) >= 3 && token == null) {
            System.out.println("Handle invalido - token nula. Cadastre ou entre primeiro.");
            return null;
        } else {
            switch (input) {
                case "1" -> {
                    ultimaOperacao = "cadastrarEmpresa";
                    ultimoOutput = cadastrar();
                    break;
                }
                case "2" -> {
                    ultimaOperacao = "loginEmpresa";
                    ultimoOutput = login();
                    break;
                }
                case "3" -> {
                    ultimaOperacao = "visualizarEmpresa";
                    ultimoOutput = visualizar(token);
                    break;
                }
                case "4" -> {
                    ultimaOperacao = "apagarEmpresa";
                    ultimoOutput = apagar(token);
                    break;
                }
                case "5" -> {
                    ultimaOperacao = "atualizarCandidato";
                    ultimoOutput = atualizar(token);
                    break;
                }
                case "6" -> {
                    ultimaOperacao = "logout";
                    ultimoOutput = logout(token);
                    break;
                }

                case "7" -> {
                    ultimaOperacao = "cadastrarVaga";
                    ultimoOutput = cadastrarVaga(token);
                    break;
                }
                case "8" -> {
                    ultimaOperacao = "visualizarCompetenciaExperiencia";
                    ultimoOutput = visualizarVaga(token);
                    break;
                }
                case "9" -> {
                    ultimaOperacao = "atualizarVaga";
                    ultimoOutput = atualizarVaga(token);
                    break;
                }
                case "10" -> {
                    ultimaOperacao = "apagarVaga";
                    ultimoOutput = apagarVaga(token);
                    break;
                }
                case "11" -> {
                    ultimaOperacao = "listarVagas";
                    ultimoOutput = listarVagas(token);
                    break;
                }
                default -> {
                    System.out.println("Entrada invalida.");
                    return null;
                }
            }
        }
        return ultimoOutput;
    }

    //empresa
    public String cadastrar() throws IOException {
        System.out.println("Digite a razao social a cadastrar.");
        razaoSocial = stdIn.readLine();
        System.out.println("Digite o CNPJ a cadastrar.");
        cnpj = stdIn.readLine();
        System.out.println("Digite o email a cadastrar.");
        email = stdIn.readLine();
        System.out.println("Digite a senha a cadastrar.");
        senha = stdIn.readLine();
        System.out.println("Digite a descricao a cadastrar.");
        descricao = stdIn.readLine();
        System.out.println("Digite o ramo a cadastrar.");
        ramo = stdIn.readLine();
        output = compilador.cadastrarEmpresa(razaoSocial, email, cnpj, senha, descricao, ramo);
        System.out.println("Output: " + output);
        return output;
    }

    public String login() throws IOException {
        System.out.println("Digite o email de login.");
        email = stdIn.readLine();
        System.out.println("Digite a senha de login.");
        senha = stdIn.readLine();
        output = compilador.loginEmpresa(email, senha);
        System.out.println("Output: " + output);
        return output;
    }

    public String visualizar(String token) throws IOException {
        System.out.println("Digite o email da empresa a visualizar.");
        email = stdIn.readLine();
        output = compilador.visualizarEmpresa(email, token);
        System.out.println("Output: " + output);
        return output;
    }

    public String apagar(String token) throws IOException {
        System.out.println("Digite o email da empresa a apagar.");
        email = stdIn.readLine();
        output = compilador.apagarEmpresa(email, token);
        System.out.println("Output: " + output);
        return output;
    }

    public String atualizar(String token) throws IOException {
        System.out.println("Digite o email da empresa a atualizar.");
        email = stdIn.readLine();
        System.out.println("Digite a razão social a atualizar.");
        razaoSocial = stdIn.readLine();
        System.out.println("Digite o CNPJ a atualizar.");
        cnpj = stdIn.readLine();
        System.out.println("Digite a senha a atualizar.");
        senha = stdIn.readLine();
        System.out.println("Digite a descricao a atualizar.");
        descricao = stdIn.readLine();
        System.out.println("Digite o ramo a atualizar.");
        ramo = stdIn.readLine();
        output = compilador.atualizarEmpresa(
                email, razaoSocial, cnpj, senha, descricao, ramo, token);
        return output;
    }
    
    //vagas
    public String cadastrarVaga(String token) throws IOException, NumberFormatException {
        boolean fecharLoop = false;
        boolean compValida = false;
        boolean escolhaValida = false;
        ArrayList<String> competencias = new ArrayList<>();
        System.out.println("Digite o email da empresa para inserir uma vaga.");
        email = stdIn.readLine();
        System.out.println("Digite o titulo da vaga.");
        nome = stdIn.readLine();
        System.out.println("Digite a faixa salarial da vaga.");
        faixaSalarial = Double.parseDouble(stdIn.readLine());
        System.out.println("Digite a descricao da vaga.");
        descricao = stdIn.readLine();
        while (!escolhaValida) {
            System.out.println("Deseja definir a vaga como:"
                    + "\n1 - Disponivel"
                    + "\n 2 - Divulgavel");
            estado = stdIn.readLine();
            switch (estado) {
                case "1" -> {
                    estado = "disponivel";
                    escolhaValida = true;
                }
                case "2" -> {
                    estado = "divulgavel";
                    escolhaValida = true;
                }
                default -> {
                    System.out.println("Entrada invalida.");
                }
            }
        }
        escolhaValida = false; //sera usado no prox loop
        while (!fecharLoop) {
            while (!compValida) {
                System.out.println("""
                   Selecione a competencia que deseja inserir.
                   1 - Python
                   2 - C#
                   3 - C++
                   4 - JS
                   5 - PHP
                   6 - Swift
                   7 - Java
                   8 - Go
                   9 - SQL
                   10 - Ruby
                   11 - HTML
                   12 - CSS
                   13 - NOSQL
                   14 - Flutter
                   15 - TypeScript
                   16 - Perl
                   17 - Cobol
                   18 - dotNet
                   19 - Kotlin
                   20 - Dart""");
                competencia = stdIn.readLine();
                switch (competencia) {
                    case "1":
                        competencia = "Python";
                        compValida = true;
                        break;
                    case "2":
                        competencia = "C#";
                        compValida = true;
                        break;
                    case "3":
                        competencia = "C++";
                        compValida = true;
                        break;
                    case "4":
                        competencia = "JS";
                        compValida = true;
                        break;
                    case "5":
                        competencia = "PHP";
                        compValida = true;
                        break;
                    case "6":
                        competencia = "Swift";
                        compValida = true;
                        break;
                    case "7":
                        competencia = "Java";
                        compValida = true;
                        break;
                    case "8":
                        competencia = "Go";
                        compValida = true;
                        break;
                    case "9":
                        competencia = "SQL";
                        compValida = true;
                        break;
                    case "10":
                        competencia = "Ruby";
                        compValida = true;
                        break;
                    case "11":
                        competencia = "HTML";
                        compValida = true;
                        break;
                    case "12":
                        competencia = "CSS";
                        compValida = true;
                        break;
                    case "13":
                        competencia = "NOSQL";
                        compValida = true;
                        break;
                    case "14":
                        competencia = "Flutter";
                        compValida = true;
                        break;
                    case "15":
                        competencia = "Typescript";
                        compValida = true;
                        break;
                    case "16":
                        competencia = "Perl";
                        compValida = true;
                        break;
                    case "17":
                        competencia = "Cobol";
                        compValida = true;
                        break;
                    case "18":
                        competencia = "dotNet";
                        compValida = true;
                        break;
                    case "19":
                        competencia = "Kotlin";
                        compValida = true;
                        break;
                    case "20":
                        competencia = "Dart";
                        compValida = true;
                        break;
                    default:
                        System.out.println("Entrada invalida. Tente novamente.");
                        compValida = false;
                        break;
                }
            }
            competencias.add(competencia);
            System.out.println("Competencia adicionada: " + competencia);
            escolhaValida = false;
            while (!escolhaValida) {
                System.out.println("Deseja adicionar mais uma competencia?"
                        + "1 - Sim"
                        + "2 - Nao");
                String escolha = stdIn.readLine();
                switch (escolha) {
                    case "1":
                        fecharLoop = false;
                        compValida = false; //restart loop
                        escolhaValida = true;
                        break;
                    case "2":
                        fecharLoop = true;
                        compValida = true;
                        escolhaValida = true;
                        break;
                    default:
                        System.out.println("Entrada invalida. Tente novamente.");
                        escolhaValida = false;
                        break;
                } //switch
            } //loop de insercao
        } //loop principal
        output = compilador.cadastrarVaga(nome, email, faixaSalarial, descricao, estado, competencias.toArray(String[]::new), token);
        System.out.println("Output: " + output);
        return output;
    }

    public String visualizarVaga(String token) throws IOException {
        System.out.println("Digite o email da empresa relacionada a vaga.");
        email = stdIn.readLine();
        System.out.println("Digite o id da vaga.");
        id = Integer.parseInt(stdIn.readLine());
        output = compilador.visualizarVaga(id, email, token);
        System.out.println("Output: " + output);
        return output;
    }

    public String atualizarVaga(String token) throws IOException {
        boolean fecharLoop = false;
        boolean compValida = false;
        boolean escolhaValida = false;
        ArrayList<String> competencias = new ArrayList<>();
        System.out.println("Digite o email da empresa para atualizar uma vaga.");
        email = stdIn.readLine();
        System.out.println("Digite o ID da vaga em questao.");
        id = Integer.parseInt(stdIn.readLine());
        System.out.println("Digite o titulo da vaga.");
        nome = stdIn.readLine();
        System.out.println("Digite a faixa salarial da vaga.");
        faixaSalarial = Double.parseDouble(stdIn.readLine());
        System.out.println("Digite a descricao da vaga.");
        descricao = stdIn.readLine();
        while (!escolhaValida) {
            System.out.println("Deseja definir a vaga como:"
                    + "\n1 - Disponivel"
                    + "\n2 - Divulgavel");
            estado = stdIn.readLine();
            switch (estado) {
                case "1" -> {
                    estado = "disponivel";
                    escolhaValida = true;
                }
                case "2" -> {
                    estado = "divulgavel";
                    escolhaValida = true;
                }
                default -> {
                    System.out.println("Entrada invalida.");
                }
            }
        }
        escolhaValida = false; //sera usado no prox loop
        while (!fecharLoop) {
            while (!compValida) {
                System.out.println("""
                   Selecione a competencia que deseja inserir.
                   1 - Python
                   2 - C#
                   3 - C++
                   4 - JS
                   5 - PHP
                   6 - Swift
                   7 - Java
                   8 - Go
                   9 - SQL
                   10 - Ruby
                   11 - HTML
                   12 - CSS
                   13 - NOSQL
                   14 - Flutter
                   15 - TypeScript
                   16 - Perl
                   17 - Cobol
                   18 - dotNet
                   19 - Kotlin
                   20 - Dart""");
                competencia = stdIn.readLine();
                switch (competencia) {
                    case "1":
                        competencia = "Python";
                        compValida = true;
                        break;
                    case "2":
                        competencia = "C#";
                        compValida = true;
                        break;
                    case "3":
                        competencia = "C++";
                        compValida = true;
                        break;
                    case "4":
                        competencia = "JS";
                        compValida = true;
                        break;
                    case "5":
                        competencia = "PHP";
                        compValida = true;
                        break;
                    case "6":
                        competencia = "Swift";
                        compValida = true;
                        break;
                    case "7":
                        competencia = "Java";
                        compValida = true;
                        break;
                    case "8":
                        competencia = "Go";
                        compValida = true;
                        break;
                    case "9":
                        competencia = "SQL";
                        compValida = true;
                        break;
                    case "10":
                        competencia = "Ruby";
                        compValida = true;
                        break;
                    case "11":
                        competencia = "HTML";
                        compValida = true;
                        break;
                    case "12":
                        competencia = "CSS";
                        compValida = true;
                        break;
                    case "13":
                        competencia = "NOSQL";
                        compValida = true;
                        break;
                    case "14":
                        competencia = "Flutter";
                        compValida = true;
                        break;
                    case "15":
                        competencia = "Typescript";
                        compValida = true;
                        break;
                    case "16":
                        competencia = "Perl";
                        compValida = true;
                        break;
                    case "17":
                        competencia = "Cobol";
                        compValida = true;
                        break;
                    case "18":
                        competencia = "dotNet";
                        compValida = true;
                        break;
                    case "19":
                        competencia = "Kotlin";
                        compValida = true;
                        break;
                    case "20":
                        competencia = "Dart";
                        compValida = true;
                        break;
                    default:
                        System.out.println("Entrada invalida. Tente novamente.");
                        compValida = false;
                        break;
                }
            }
            competencias.add(competencia);
            System.out.println("Competencia adicionada: " + competencia);
            escolhaValida = false;
            while (!escolhaValida) {
                System.out.println("Deseja adicionar mais uma competencia?"
                        + "1 - Sim"
                        + "2 - Nao");
                String escolha = stdIn.readLine();
                switch (escolha) {
                    case "1":
                        fecharLoop = false;
                        compValida = false; //restart loop
                        escolhaValida = true;
                        break;
                    case "2":
                        fecharLoop = true;
                        compValida = true;
                        escolhaValida = true;
                        break;
                    default:
                        System.out.println("Entrada invalida. Tente novamente.");
                        escolhaValida = false;
                        break;
                } //switch
            } //loop de insercao
        } //loop principal
        output = compilador.atualizarVaga(id, nome, email, faixaSalarial, descricao, estado, competencias.toArray(String[]::new), token);
        System.out.println("Output: " + output);
        return output;
    }
    
    public String apagarVaga(String token) throws IOException {
        System.out.println("Digite o email vinculado a empresa que criou a vaga.");
        email = stdIn.readLine();
        System.out.println("Digite o ID da vaga a apagar.");
        id = Integer.parseInt(stdIn.readLine());
        output = compilador.apagarVaga(id, email, token);
        System.out.println("Output: " + output);
        return output;
    }
    
    public String listarVagas(String token) throws IOException {
        System.out.println("Digite o email vinculado a empresa que criou as vagas.");
        email = stdIn.readLine();
        output = compilador.listarVagas(email, token);
        System.out.println("Output: " + output);
        return output;
    }

    //generico


    public String logout(String token) {
        output = compilador.logout(token);
        return output;
    }
}
