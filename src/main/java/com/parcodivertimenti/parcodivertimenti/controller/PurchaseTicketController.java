package com.parcodivertimenti.parcodivertimenti.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;

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
        // Simulazione dell'invio della mail con i dettagli del biglietto (da implementare)

    }
}
