package servidor;

import java.net.*;
import java.io.*;
import jsonmanager.InterpretadorJson;
import java.sql.SQLException;

public class Servidor extends Thread {

    protected static boolean serverContinue = true;
    protected Socket clientSocket;
    protected InterpretadorJson interpretador;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        //cria socket
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Digite a porta a utilizar.");
            int port = Integer.parseInt(reader.readLine());
            serverSocket = new ServerSocket(port);
            System.out.println("Socket criado");
            System.out.println(InetAddress.getLocalHost() + "\n"
                    + "Porta: " + port + "\nAguardando conexao...");
            try {
                while (serverContinue) {
                    try {
                        new Servidor(serverSocket.accept());
                    } catch (SocketTimeoutException ste) {
                        System.err.println("Timeout!");
                    }
                }
            } catch (IOException e) {
                System.err.println("Accept falhou.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Port ocupado.");
            System.exit(1);
        } finally {
            try {
                System.out.println("Fechando socket...");
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Nao foi possivel fechar o port.");
                System.exit(1);
            }
        }
    }

    private Servidor(Socket clientSoc) {
        clientSocket = clientSoc;
        start();
    }

    public void run() {
        System.out.println("--- Nova thread de comunicação iniciada - socket " + clientSocket + " ---");
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                    true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            interpretador = new InterpretadorJson(); //interpretador eh a classe responsavel por processar todos os requests
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Cliente: " + inputLine);
                String output = interpretador.processarRequest(inputLine);
                System.out.println("Output: " + output);
                out.println(output);
            }
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) { //TODO: melhorar handling de exceptions
            System.err.println("Socket " + clientSocket + " fechada");
        } catch (NullPointerException e) {
            System.out.println("WARNING! Nullpointer: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Objeto nao eh um json!" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de BD! Analise a mensagem abaixo e certifique-se que o mesmo esta rodando.\n" + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Excecao desconhecida: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
