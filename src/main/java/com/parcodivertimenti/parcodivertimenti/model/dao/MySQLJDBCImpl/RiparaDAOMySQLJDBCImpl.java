package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.dao.riparaDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.ripara;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiparaDAOMySQLJDBCImpl implements riparaDAO {
    private final String COUNTER_ID = "addettoGiostreId";
    Connection conn;

    public RiparaDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override /*da implementare se sito prevede di potersi registrare (stessa cosa per update e delete)*/
    public ripara create(String cf_manutentore, Long codice_attrazione, String descrizione){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(ripara Ripara){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(ripara Ripara){
        throw new UnsupportedOperationException("Not supported yet");
    }


    @Override
    public ripara findByCFManutentore(String cf_manutentore){
        PreparedStatement ps;
        ripara ag = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM ag"
                    + "WHERE"
                    + "cf_manutentore = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,cf_manutentore); /* 1 è l'indice del punto di domanda*/

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                ag = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return ag;
    }

    @Override
    public ripara findByCodiceAttrazione(Long codice_attrazione){
        PreparedStatement ps;
        ripara ag = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM ag"
                    + "WHERE"
                    + "codice_attrazione = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, codice_attrazione);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                ag = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return ag;
    }


    ripara read(ResultSet rs){
        ripara ag = new ripara();
        try{
            ag.setCf_manutentore(rs.getString("cf_manutentore"));
        }catch (SQLException sqle){
        }
        try{
            ag.setCodice_attrazione(rs.getLong("codice_attrazione"));
        }catch (SQLException sqle){
        }
        try{
            ag.setDescrizione(rs.getString("descrizione"));
        }catch (SQLException sqle){
        }


        return ag;
    }


}
