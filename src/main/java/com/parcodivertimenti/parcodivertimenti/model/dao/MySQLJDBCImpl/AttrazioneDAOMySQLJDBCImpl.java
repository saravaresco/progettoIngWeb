package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.attrazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AttrazioneDAOMySQLJDBCImpl {

    private final String COUNTER_ID = "attrazioneId";
    Connection conn;

    public AttrazioneDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public attrazione create(Long codice, String nome, String tipologia, Date data_ultima_manutenzione){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(attrazione att){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(attrazione att){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public attrazione findLoggedUser(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public attrazione findByCodice(Long codice){
        PreparedStatement ps;
        attrazione at = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM at"
                    + "WHERE"
                    + "codice = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1,codice);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                at = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return at;
    }

    @Override
    public attrazione findByNome(String nome){
        PreparedStatement ps;
        attrazione at = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM at"
                    + "WHERE"
                    + "nome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                at = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return at;
    }

    @Override
    public attrazione findByTipologia(String tipologia){
        PreparedStatement ps;
        attrazione at = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM at"
                    + "WHERE"
                    + "tipologia = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, tipologia);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                at = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return at;
    }

    @Override
    public attrazione findByDate(Date data_ultima_manutenzione){
        PreparedStatement ps;
        attrazione at = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM at"
                    + "WHERE"
                    + "data_ultima_manutenzione = ?";

            ps = conn.prepareStatement(sql);
            ps.setDate(1, data_ultima_manutenzione);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                at = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return at;
    }

    attrazione read(ResultSet rs){
        attrazione at = new attrazione();
        try{
            at.setCodice(rs.getLong("codice"));
        }catch (SQLException sqle){
        }
        try{
            at.setNome(rs.getString("nome"));
        }catch (SQLException sqle){
        }
        try{
            at.setTipologia(rs.getString("tipologia"));
        }catch (SQLException sqle){
        }
        try{
            at.setData_ultima_manutenzione(rs.getDate("data_ultima_manutenzione"));
        }catch (SQLException sqle){
        }

        return at;
    }
}
