package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.dao.bigliettoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BigliettoDAOMySQLJDBCImpl implements bigliettoDAO {

    private final String COUNTER_ID = "bigliettoId";
    Connection conn;

    public BigliettoDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public biglietto create(Long ID, String codice_fiscale, Long prezzo, Date data_acquisto, String tipoligia1, String tipologia2, String mail){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(biglietto bigl){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(biglietto bigl){
        throw new UnsupportedOperationException("Not supported yet");
    }


    @Override
    public biglietto findById(Long ID){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM at"
                    + "WHERE"
                    + "ID = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1,ID);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    @Override
    public List<biglietto> findByCF(String codiceFiscale) {
        List<biglietto> tickets = new ArrayList<>();
        String sql = "SELECT * FROM biglietto WHERE CODICE_FISCALE_V = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codiceFiscale);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    biglietto ticket = new biglietto();
                    ticket.setID(rs.getLong("id"));
                    ticket.setData_acquisto(rs.getDate("data_acquisto"));
                    ticket.setTipologia1(rs.getString("tipologia1"));
                    ticket.setTipologia2(rs.getString("tipologia2"));
                    ticket.setPrezzo(rs.getLong("prezzo"));
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero dei biglietti", e);
        }

        return tickets;
    }

    @Override
    public biglietto findByData(Date data_acquisto){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM b"
                    + "WHERE"
                    + "data_acquisto = ?";

            ps = conn.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) data_acquisto);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    @Override
    public biglietto findByTipologia1(String tipologia1){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM b"
                    + "WHERE"
                    + "tipologia1 = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, tipologia1);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    @Override
    public biglietto findByTipologia2(String tipologia2){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM b"
                    + "WHERE"
                    + "tipologia2 = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, tipologia2);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    @Override
    public biglietto findByMail(String mail){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM b"
                    + "WHERE"
                    + "mail = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, mail);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    biglietto read(ResultSet rs){
        biglietto b = new biglietto();
        try{
            b.setID(rs.getLong("ID"));
        }catch (SQLException sqle){
        }
        try{
            b.setCodice_fiscale(rs.getString("codice_fiscale"));
        }catch (SQLException sqle){
        }
        try{
            b.setPrezzo(rs.getLong("prezzo"));
        }catch (SQLException sqle){
        }
        try{
            b.setData_acquisto(rs.getDate("data_acquisto"));
        }catch (SQLException sqle){
        }

        try{
            b.setTipologia1(rs.getString("tipologia1"));
        }catch (SQLException sqle){
        }

        try{
            b.setTipologia2(rs.getString("tipologia2"));
        }catch (SQLException sqle){
        }

        try{
            b.setMail(rs.getString("mail"));
        }catch (SQLException sqle){
        }

        return b;
    }
}
