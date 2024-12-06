package br.edu.ifsp.ppd.socket;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            SMTPServer server = new SMTPServer(2525); // instancia um novo servidor
            server.start();// da start no servidor
        } catch (IOException e) {// tratamento de IOException
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
    
}
