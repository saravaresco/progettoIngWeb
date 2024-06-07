package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.manutentore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ManutentoreDAOMySQLJDBCImpl {

    private final String COUNTER_ID = "manutentoreId";
    Connection conn;

    public ManutentoreDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public manutentore create(String codice_fiscale, Long numero_interventi){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(manutentore manut){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(manutentore manut){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public manutentore findLoggedUser(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public manutentore findByCF(String codice_fiscale){
        PreparedStatement ps;
        manutentore m = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM m"
                    + "WHERE"
                    + "codice_fiscale = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,codice_fiscale);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                m = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return m;
    }

    @Override
    public manutentore findByNumeroInt(Long numero_interventi){
        PreparedStatement ps;
        manutentore m = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM m"
                    + "WHERE"
                    + "numero_interventi = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, numero_interventi);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                m = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return m;
    }


    manutentore read(ResultSet rs){
        manutentore m = new manutentore();
        try{
            m.setCodice_fiscale(rs.getString("codice_fiscale"));
        }catch (SQLException sqle){
        }
        try{
            m.setNumero_interventi(rs.getLong("numero_interventi"));
        }catch (SQLException sqle){
        }

        return m;
    }
}
