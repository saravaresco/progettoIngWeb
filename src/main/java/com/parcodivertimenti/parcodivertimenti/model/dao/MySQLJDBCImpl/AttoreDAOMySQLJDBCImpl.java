package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.dao.attoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.attore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.module.ModuleDescriptor.read;
public class AttoreDAOMySQLJDBCImpl implements attoreDAO {

    private final String COUNTER_ID = "attoreId";
    Connection conn;

    public AttoreDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public attore create(String codice_fiscale, String ruolo, String username, String password){
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

            while(resultSet.next()){
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
    public attore findByUsername(String username) {

        PreparedStatement ps;
        attore at = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM at "
                    + " WHERE "
                    + "   username = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                at = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return at;

    }

    attore read(ResultSet rs){
        attore at = new attore();
        try{
            at.setCodice_fiscale(rs.getString("CODICE_FISCALE"));
        }catch (SQLException sqle){
        }
        try{
            at.setRuolo(rs.getString("RUOLO"));
        }catch (SQLException sqle){
        }
        try{
            at.setUsername(rs.getString("USERNAME"));
        }catch (SQLException sqle){
        }
        try{
            at.setPassword(rs.getString("PASSWORD"));
        }catch (SQLException sqle){
        }

        return at;
    }
}
