package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.attore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.module.ModuleDescriptor.read;
public class AttoreDAOMySQLJDBCImpl {

    private final String COUNTER_ID = "attoreId";
    Connection conn;

    public AttoreDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public attore create(String codice_fiscale, String ruolo){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(attore att){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(attore att){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public attore findLoggedUser(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public attore findByUserCF(String codice_fiscale){
        PreparedStatement ps;
        attore at = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM at"
                    + "WHERE"
                    + "codice_fiscale = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,codice_fiscale);

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
    public attore findByRuolo(String ruolo){
        PreparedStatement ps;
        attore at = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM at"
                    + "WHERE"
                    + "ruolo = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, ruolo);

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

    attore read(ResultSet rs){
        attore at = new attore();
        try{
            at.setCodice_fiscale(rs.getString("codice_fiscale"));
        }catch (SQLException sqle){
        }
        try{
            at.setRuolo(rs.getString("ruolo"));
        }catch (SQLException sqle){
        }

        return at;
    }
}
