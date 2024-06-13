package cliente;

import entidades.CompexSimples;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import jsonmanager.CompiladorJsonCliente;

public class MenuCandidato {

    private String nome = null;
    private String email = null;
    private String senha = null;
    private String output = null;
    private String competencia = null;
    private int experiencia;
    private String input = null;
    private String filtro = null;
    private String ultimaOperacao = null;
    private String ultimoOutput = null;
    private BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    CompiladorJsonCliente compilador = new CompiladorJsonCliente();

    public MenuCandidato() {
        System.out.println("MenuCandidato instanciado!");
    }

    public String handle(String token) throws IOException {
        System.out.println("Handle iniciado - Candidato");
        System.out.println("""
                            Selecione uma operacao.
                           --- Candidato ---
                            1 - Cadastrar
                            2 - Login
                            3 - Visualizar
                            4 - Apagar
                            5 - Atualizar
                            6 - Logout
                           --- Competencia/Experiencia ---
                           7 - Inserir
                           8 - Visualizar
                           9 - Atualizar
                           10 - Apagar
                           --- Vagas ---
                           11 - Filtrar""");
        input = stdIn.readLine();
        if (Integer.parseInt(input) >= 3 && token == null) {
            System.out.println("Handle invalido - token nula. Cadastre ou entre primeiro.");
            return null;
        } else {
            switch (input) {
                case "1" -> {
                    ultimaOperacao = "cadastrarCandidato";
                    ultimoOutput = cadastrar();
                    break;
                }
                case "2" -> {
                    ultimaOperacao = "loginCandidato";
                    ultimoOutput = login();
                    break;
                }
                case "3" -> {
                    ultimaOperacao = "visualizarCandidato";
                    ultimoOutput = visualizar(token);
                    break;
                }
                case "4" -> {
                    ultimaOperacao = "apagarCandidato";
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
                    ultimaOperacao = "cadastrarCompetenciaExperiencia";
                    ultimoOutput = inserirCompEx(token);
                    break;
                }
                case "8" -> {
                    ultimaOperacao = "visualizarCompetenciaExperiencia";
                    ultimoOutput = visualizarCompEx(token);
                    break;
                }
                case "9" -> {
                    ultimaOperacao = "atualizarCompetenciaExperiencia";
                    ultimoOutput = atualizarCompEx(token);
                    break;
                }
                case "10" -> {
                    ultimaOperacao = "apagarCompetenciaExperiencia";
                    ultimoOutput = apagarCompEx(token);
                    break;
                }
                case "11" -> {
                    ultimaOperacao = "filtrarVagas";
                    ultimoOutput = filtrarVagas(token);
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

    public String cadastrar() throws IOException {
        System.out.println("Digite o nome a cadastrar.");
        nome = stdIn.readLine();
        System.out.println("Digite o email a cadastrar.");
        email = stdIn.readLine();
        System.out.println("Digite a senha a cadastrar.");
        senha = stdIn.readLine();
        output = compilador.cadastrarCandidato(nome, email, senha);
        System.out.println("Output: " + output);
        return output;
    }

    public String login() throws IOException {
        System.out.println("Digite o email de login.");
        email = stdIn.readLine();
        System.out.println("Digite a senha de login.");
        senha = stdIn.readLine();
        output = compilador.loginCandidato(email, senha);
        System.out.println("Output: " + output);
        return output;
    }

    public String visualizar(String token) throws IOException {
        System.out.println("Digite o email do candidato a visualizar.");
        email = stdIn.readLine();
        output = compilador.visualizarCandidato(email, token);
        System.out.println("Output: " + output);
        return output;
    }

    public String apagar(String token) throws IOException {
        System.out.println("Digite o email do candidato a apagar.");
        email = stdIn.readLine();
        output = compilador.apagarCandidato(email, token);
        System.out.println("Output: " + output);
        return output;
    }

    public String atualizar(String token) throws IOException {
        System.out.println("Digite o email do candidato a atualizar.");
        email = stdIn.readLine();
        System.out.println("Digite o nome a atualizar.");
        nome = stdIn.readLine();
        System.out.println("Digite a senha a atualizar.");
        senha = stdIn.readLine();
        output = compilador.atualizarCandidato(email, nome, senha, token);
        return output;
    }

    //competencias e exp
    public String inserirCompEx(String token) throws IOException {
        boolean fecharLoop = false;
        boolean compValida = false;
        boolean escolhaValida = false;
        ArrayList<CompexSimples> compexList = new ArrayList<>();
        System.out.println("Digite o email do candidato para inserir.");
        email = stdIn.readLine();
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
            System.out.println("Digite os anos de experiencia.");
            experiencia = Integer.parseInt(stdIn.readLine());
            compexList.add(new CompexSimples(competencia, experiencia));
            System.out.println("Competencia adicionada: " + competencia + "\nExperiencia: " + experiencia);
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
        output = compilador.cadastrarCompetenciaExperiencia(email, compexList.toArray(CompexSimples[]::new), token);
        System.out.println("Output: " + output);
        return output;
    }

    public String visualizarCompEx(String token) throws IOException {
        System.out.println("Digite o email do candidato para visualizar suas competencias/experiencias.");
        email = stdIn.readLine();
        output = compilador.visualizarCompetenciaExperiencia(email, token);
        System.out.println("Output: " + output);
        return output;
    }

    public String apagarCompEx(String token) throws IOException {
        boolean fecharLoop = false;
        boolean compValida = false;
        boolean escolhaValida = false;
        ArrayList<CompexSimples> compexList = new ArrayList<>();
        System.out.println("Digite o email do candidato para apagar.");
        email = stdIn.readLine();
        while (!fecharLoop) {
            while (!compValida) {
                System.out.println("""
                   Selecione a competencia que deseja apagar.
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
            System.out.println("Digite os anos de experiencia.");
            experiencia = Integer.parseInt(stdIn.readLine());
            compexList.add(new CompexSimples(competencia, experiencia));
            System.out.println("Competencia adicionada: " + competencia + experiencia);
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
        output = compilador.apagarCompetenciaExperiencia(email, compexList.toArray(new CompexSimples[0]), token);
        System.out.println("Output: " + output);
        return output;
    }

    public String atualizarCompEx(String token) throws IOException {
        boolean fecharLoop = false;
        boolean compValida = false;
        boolean escolhaValida = false;
        ArrayList<CompexSimples> compexList = new ArrayList<>();
        System.out.println("Digite o email do candidato para atualizar.");
        email = stdIn.readLine();
        while (!fecharLoop) {
            while (!compValida) {
                System.out.println("""
                   Selecione a competencia que deseja atualizar.
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
            System.out.println("Digite os anos de experiencia.");
            experiencia = Integer.parseInt(stdIn.readLine());
            compexList.add(new CompexSimples(competencia, experiencia));
            System.out.println("Competencia adicionada: " + competencia + experiencia);
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
        output = compilador.atualizarCompetenciaExperiencia(email, compexList.toArray(new CompexSimples[0]), token);
        System.out.println("Output: " + output);
        return output;
    }

    public String filtrarVagas(String token) throws IOException {
        boolean fecharLoop = false;
        boolean compValida = false;
        boolean escolhaValida = false;
        ArrayList<String> competencias = new ArrayList<>();
        while (!fecharLoop) {
            while (!compValida) {
                System.out.println("""
                   Selecione a competencia que deseja atualizar.
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
            System.out.println("Competencia adicionada: " + competencia + experiencia);
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
        boolean tipoValido = false;
        while (!tipoValido) {
            System.out.println("Selecione o tipo do filtro."
                    + "1 - AND"
                    + "2 - OR");
            filtro = stdIn.readLine();
            switch (filtro) {
                case "1" -> {
                    filtro = "and";
                    tipoValido = true;
                    break;
                }
                case "2" -> {
                    filtro = "or";
                    tipoValido = true;
                    break;
                }
                default -> {
                    System.out.println("Entrada invalida.");
                    break;
                }
            }
        }
        output = compilador.filtrarVagas(competencias.toArray(new String[competencias.size()]), filtro, token);
        System.out.println("Output: " + output);
        return output;
    }

    public String logout(String token) {
        output = compilador.logout(token);
        return output;
    }
}
