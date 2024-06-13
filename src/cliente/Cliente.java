package cliente;

import java.io.*;
import java.net.*;
import com.google.gson.*;
import jsonmanager.CompiladorJsonCliente;

public class Cliente {

    public static void main(String[] args) throws IOException {
        CompiladorJsonCliente compilador = new CompiladorJsonCliente();
        //MenuEmpresa menuEmpresa = new MenuEmpresa();
        MenuCandidato menuCandidato = new MenuCandidato();
        MenuEmpresa menuEmpresa = new MenuEmpresa();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String retorno = null; //recebido do server
        String token = null;
        String output;
        boolean esperandoRetorno = true; //controla se o cliente deve esperar o server
        String ultimaOperacao = null;

        System.out.println("Insira o IP do servidor.");
        String serverHostname = reader.readLine();
        System.out.println("Insira o port do servidor.");
        int port = Integer.parseInt(reader.readLine());

        if (args.length > 0) {
            serverHostname = args[0];
        }
        System.out.println("Conectando a " + serverHostname + ":" + port + "...");

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try { //conexao
            echoSocket = new Socket(serverHostname, 22222);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Excecao de I/O ao conectar.");
            System.exit(1);
        }
        //conexao realizada!
        try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            System.out.println("""
                Insira um numero.
                1 - Cliente
                2 - Empresa
                0 - Sair""");
            while ((userInput = stdIn.readLine()) != null) {
                esperandoRetorno = true; //processar nova request
                switch (userInput) {
                    case "1": //menu candidato TODO handle null do menuCandidato - NAO ENVIE
                        output = menuCandidato.handle(token);
                        if (output != null) {
                            out.println(output);
                            break;
                        }
                    case "2":
                        output = menuEmpresa.handle(token);
                        if (output != null) {
                            out.println(output);
                            break;
                        }
                        break;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Entrada invalida.");
                        esperandoRetorno = true;
                };
                retorno = in.readLine();
                if (compilador.checkOperacao(retorno, "logout") || compilador.checkOperacao(retorno, "apagarEmpresa") || compilador.checkOperacao(retorno, "apagarCandidato"))  {
                    token = null;
                    System.out.println("Token de sessao redefinida.");
                }
                if (token == null && retorno != null) {
                    if (compilador.definirTokenSessao(retorno) != null) { //se a op foi login
                        token = compilador.definirTokenSessao(retorno);
                        if (token == null) {
                            System.out.println("Login falhou! Nao foi possivel definir uma token.");
                        }
                        System.out.println("Token definida:" + token);
                    }
                }
                System.out.println("Retorno: " + retorno);

                System.out.println("""
                Insira um numero.
                1 - Cliente
                2 - Empresa
                0 - Sair""");
            }
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        } catch (SocketException e) {
            System.out.println("Conexao terminada - Socket fechada.");
            System.exit(0);
        }
    }
}
