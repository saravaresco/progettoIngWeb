package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.dipendente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DipendenteDAOMySQLJDBCImpl {

    private final String COUNTER_ID = "dipendenteId";
    Connection conn;

    public DipendenteDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public dipendente create(String codice_fiscale, String nome, String cognome, Date data_nascita, String sesso, Long stipendio){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(dipendente dip){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(dipendente dip){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public dipendente findLoggedUser(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public dipendente findByCF(String codice_fiscale){
        PreparedStatement ps;
        dipendente dip = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM dip"
                    + "WHERE"
                    + "codice_fiscale = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,codice_fiscale);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                dip = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return dip;
    }

    @Override
    public dipendente findByNome(String nome){
        PreparedStatement ps;
        dipendente dip = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM dip"
                    + "WHERE"
                    + "nome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                dip = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return dip;
    }

    @Override
    public dipendente findByCognome(String cognome){
        PreparedStatement ps;
        dipendente dip = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM dip"
                    + "WHERE"
                    + "cognome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cognome);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                dip = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return dip;
    }

    @Override
    public dipendente findByDate(Date data_nascita){
        PreparedStatement ps;
        dipendente dip = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM dip"
                    + "WHERE"
                    + "data_nascita = ?";

            ps = conn.prepareStatement(sql);
            ps.setDate(1, data_nascita);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                dip = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return dip;
    }

    @Override
    public dipendente findByStipendio(Long stipendio){
        PreparedStatement ps;
        dipendente dip = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM dip"
                    + "WHERE"
                    + "stipendio = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, stipendio);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                dip = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return dip;
    }

    dipendente read(ResultSet rs){
        dipendente dip = new dipendente();
        try{
            dip.setCodice_fiscale(rs.getString("codice_fiscale"));
        }catch (SQLException sqle){
        }
        try{
            dip.setNome(rs.getString("nome"));
        }catch (SQLException sqle){
        }
        try{
            dip.setCognome(rs.getString("cognome"));
        }catch (SQLException sqle){
        }
        try{
            dip.setData_nascita(rs.getDate("data_nascita"));
        }catch (SQLException sqle){
        }
        try{
            dip.setSesso(rs.getString("sesso"));
        }catch (SQLException sqle){
        }
        try{
            dip.setStipendio(rs.getLong("stipendio"));
        }catch (SQLException sqle){
        }

        return dip;
    }
}
