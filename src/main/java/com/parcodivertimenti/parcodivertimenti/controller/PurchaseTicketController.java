package com.parcodivertimenti.parcodivertimenti.controller;


//import com.mysql.cj.Session;
import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;

import jakarta.mail.*;
import jakarta.mail.Session;
import jakarta.mail.internet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@WebServlet("/purchaseTicket")
public class PurchaseTicketController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/parco_web";
        String jdbcUsername = "root";
        String jdbcPassword = "sarA2002";
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String codiceFiscale = request.getParameter("codiceFiscale");
        String mail = request.getParameter("mail");
        String tipologia1 = request.getParameter("tipologia1");
        String tipologia2 = request.getParameter("tipologia2");

        double prezzoBiglietto = calcolaPrezzo(tipologia1, tipologia2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataAcquisto = sdf.format(new Date());

        biglietto biglietto = new biglietto();


        // Esegui il pagamento
        String metodoPagamento = request.getParameter("metodoPagamento");
        if ("carta".equals(metodoPagamento)) {
            // Gestione pagamento con carta
            String numeroCarta = request.getParameter("numeroCarta");
            String scadenzaCarta = request.getParameter("scadenzaCarta");
            String cvv = request.getParameter("cvv");

            // Simula il pagamento con la carta (da implementare)
            boolean pagamentoEffettuato = effettuaPagamentoConCarta(numeroCarta, scadenzaCarta, cvv, prezzoBiglietto);
            if (pagamentoEffettuato) {
                salvaBigliettiNelDatabase((List<com.parcodivertimenti.parcodivertimenti.model.mo.biglietto>) biglietto);
                inviaMailConBiglietti((List<com.parcodivertimenti.parcodivertimenti.model.mo.biglietto>) biglietto);
                response.sendRedirect("ticketConfirmation.jsp");
            } else {
                request.setAttribute("errorMessage", "Errore durante il pagamento con la carta.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            // L'utente paga alla cassa e ritira in loco
            salvaBigliettiNelDatabase((List<com.parcodivertimenti.parcodivertimenti.model.mo.biglietto>) biglietto);
            inviaMailConBiglietti((List<com.parcodivertimenti.parcodivertimenti.model.mo.biglietto>) biglietto);
            response.sendRedirect("ticketConfirmation.jsp");
        }
    }

    private double calcolaPrezzo(String tipologia1, String tipologia2) {
        double prezzoBase = 0.0;
        switch (tipologia1) {
            case "serale":
                prezzoBase = 20.0;
                break;
            case "giornaliero":
                prezzoBase = 40.0;
                break;
            case "abbonamento":
                prezzoBase = 100.0;
                break;
        }
        return "intero".equals(tipologia2) ? prezzoBase : prezzoBase * 0.5;
    }

    private boolean effettuaPagamentoConCarta(String numeroCarta, String scadenzaCarta, String cvv, double totale) {
        // Simulazione del pagamento con carta (qui possiamo assumere che il pagamento vada sempre a buon fine)
        System.out.println("Pagamento effettuato con successo. Dettagli carta: " + numeroCarta + ", scadenza: " + scadenzaCarta + ", CVV: " + cvv + ", Totale: " + totale);
        return true;
    }

    private void salvaBigliettiNelDatabase(List<biglietto> biglietti) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO biglietto (CODICE_FISCALE_V, PREZZO, DATA_ACQUISTO, TIPOLOGIA1, TIPOLOGIA2, MAIL) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (biglietto biglietto : biglietti) {
                    statement.setString(1, biglietto.getCodice_fiscale());
                    statement.setDouble(2, biglietto.getPrezzo());
                    statement.setTimestamp(3, new Timestamp(new Date().getTime()));
                    statement.setString(4, biglietto.getTipologia1());
                    statement.setString(5, biglietto.getTipologia2());
                    statement.setString(4, biglietto.getMail());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void inviaMailConBiglietti(List<biglietto> biglietti) {
        // Simulazione dell'invio della mail con i dettagli del biglietto
        //indirizzo email del mittente
        String from = "parcoDivertimenti2024@gmail.com";
        // SMTP server
        String host = "smtp.gmail.com";
        final String username = "parcoDivertimenti2024@gmail.com";
        final String password = "Pippo1234";

        // Proprietà per la configurazione del server SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Ottieni la sessione
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        try {
            //crea oggetto MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            // Aggiungi destinatario
            String to = biglietti.get(0).getMail();
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Oggetto della mail
            message.setSubject("Conferma acquisto biglietto");

            // Costruisci il corpo del messaggio
            StringBuilder content = new StringBuilder();
            content.append("Grazie per l'acquisto dei biglietti. Di seguito i dettagli:\n\n");
            for (biglietto b : biglietti) {
                content.append("Tipo: ").append(b.getTipologia1()).append(" - ").append(b.getTipologia2()).append("\n");
                content.append("Prezzo: ").append(b.getPrezzo()).append("\n\n");
            }
            message.setText(content.toString());

            // Invia il messaggio
            Transport.send(message);
            System.out.println("Mail inviata con successo.");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }
}
