package br.edu.ifsp.ppd.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SMTPClientHandler implements Runnable {
    private Socket clientSocket;

    public SMTPClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); //Variável para enviar para o cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //Variável para receber do cliente
        ) {
            String serverName = "localhost";
            out.println("220 " + serverName + " Simple Mail Transfer Service Ready");

            String command;
            String senderEmail = null;
            String recipientEmail = null;

            while ((command = in.readLine()) != null) {
                if (command.startsWith("HELO")) {//se o comando enviado pelo cliente começar com "HELLO" ele entra na condicional e devolve o hello em caso de email válido
                    out.println("250 Hello, pleased to meet you");
                } else if (command.startsWith("MAIL FROM:")) {// verifica se o comando enviado pelo client começa com "MAIL FORM:"
                    senderEmail = command.substring(10).trim();
                    if (isValidEmail(senderEmail)) {
                        out.println("250 Sender <" + senderEmail + "> OK");
                    } else {
                        out.println("550 Invalid sender email address");
                    }
                } else if (command.startsWith("RCPT TO:")) { //se ele receber
                    recipientEmail = command.substring(8).trim();
                    if (isValidEmail(recipientEmail)) {
                        out.println("250 Recipient <" + recipientEmail + "> OK");
                    } else {
                        out.println("550 Invalid recipient email address");
                    }
                } else if (command.equals("DATA")) {
                    out.println("354 End data with <CR><LF>.<CR><LF>");
                    StringBuilder messageData = new StringBuilder();
                    String dataLine;

                    while ((dataLine = in.readLine()) != null && !dataLine.equals(".")) {
                        messageData.append(dataLine).append("\n");
                    }

                    System.out.println("Mensagem recebida de " + senderEmail + " para " + recipientEmail + ":\n" + messageData);
                    out.println("250 Message accepted for delivery");
                } else if (command.equals("QUIT")) {
                    out.println("221 " + serverName + " Service closing transmission channel");
                    break;
                } else {
                    out.println("500 Syntax error, command unrecognized");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
        } finally {
            closeClientSocket();
        }
    }

    private boolean isValidEmail(String email) {// ele faz teste para ver se tem o @ se no começo tem caracteres especiais , retorna um booleano
        return email != null && email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private void closeClientSocket() {//fecha o clietSocket ao o cliente sair
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Erro ao fechar o socket do cliente: " + e.getMessage());
        }
    }
}
