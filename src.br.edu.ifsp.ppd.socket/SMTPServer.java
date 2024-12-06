package br.edu.ifsp.ppd.smtp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SMTPServer {
    private int port;

    public SMTPServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {// aqui ele cria o serverSocket inicindo ele
            System.out.println("SMTP Server iniciado na porta " + this.port);

            while (true) {//o while(true) para o servidor nao fechar
                Socket clientSocket = serverSocket.accept();//  aceitando as requisições para a porta vindas do usuario
                new Thread(new SMTPClientHandler(clientSocket)).start();//redirecionando o cliente para a Thread 
            }
        }
    }
}
