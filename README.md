Avaliação de Programação Paralela e Distribuída (PPD)
06 de dezembro de 2024
Instruções
1. Leia atentamente a questão e desenvolva a sua solução adicionando o máximo de
detalhes possível em sua resposta;
2. A organização da sua resposta é um aspecto importante e impactará no
desempenho final desta avaliação;
3. Garanta que o software rode;
4. Esta avaliação possui 180 minutos de duração máxima e 50 minutos de duração
mínima; e
5. A nota desta avaliação está no intervalo entre (inclusive) 0 e 10.
Boa Prova!
Implementação de ServerSocket em Java com Base na RFC 5321 (SMTP)
Objetivo: Desenvolver um servidor que implemente as funcionalidades básicas do
protocolo SMTP conforme descrito na RFC 5321.
Descrição do Problema
Nesta prova, você deverá implementar um servidor SMTP simplificado usando
ServerSocket em Java. O servidor deverá atender às seguintes especificações básicas do
protocolo SMTP:
1. Conexão e Mensagens de Boas-Vindas:
Quando um cliente se conecta ao servidor, o servidor deve enviar uma mensagem
de boas-vindas no formato:
220 <server_name> Simple Mail Transfer Service Ready
2. Comando HELO:
O cliente inicia uma sessão de envio com o comando:
MAIL FROM:<sender_email>
O servidor responde:
250 Sender <sender_email> OK
3. Comando MAIL FROM:
O cliente inicia uma sessão de envio com o comando:
MAIL FROM:<sender_email>
O servidor responde:
250 Sender <sender_email> OK
4. Comando RCPT TO:
O cliente especifica o destinatário do e-mail:
RCPT TO:<recipient_email>
O servidor responde:
250 Recipient <recipient_email> OK
5. Comando DATA:
O cliente inicia o envio do corpo do e-mail com o comando:
DATA
O servidor responde:
354 End data with <CR><LF>.<CR><LF>
O cliente envia o corpo do e-mail e finaliza com uma linha contendo apenas um
ponto (.).
O servidor responde:
250 Message accepted for delivery
6. Comando QUIT:
O cliente encerra a sessão com o comando:
QUIT
O servidor responde:
221 <server_name> Service closing transmission channel
Requisitos Técnicos
1. O servidor deve ser implementado utilizando a classe ServerSocket do Java.
2. A comunicação entre cliente e servidor será feita utilizando sockets TCP.
3. O servidor deve escutar na porta 2525.
4. Implemente validações simples:
○ E-mails devem ter o formato username@domain.com.
○ Comandos não reconhecidos devem receber a resposta:
500 Syntax error, command unrecognized
5. Utilize threads para permitir que o servidor atenda múltiplas conexões simultâneas
(um cliente por thread).
Dicas
● Leia com atenção os trechos relevantes da RFC 5321 para entender a sequência de
comandos e respostas.
● Teste seu servidor utilizando um cliente telnet:
telnet localhost 2525
● Documente seu código com comentários explicativos.