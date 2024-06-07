package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.dao.addettoGiostreDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.module.ModuleDescriptor.read;

public class AddettoGiostreDAOMySQLJDBCImpl {

    private final String COUNTER_ID = "addettoGiostreId";
    Connection conn;

    public AddettoGiostreDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public addettoGiostre create(String codice_fiscale, String mansione, Long codice_giostra){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(addettoGiostre addettogiostre){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(addettoGiostre addettogiostre){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public addettoGiostre findLoggedUser(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public addettoGiostre findByUserCF(String codice_fiscale){
        PreparedStatement ps;
        addettoGiostre ag = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM ag"
                    + "WHERE"
                    + "codice_fiscale = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,codice_fiscale);

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
    public addettoGiostre findByMansione(String mansione){
        PreparedStatement ps;
        addettoGiostre ag = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM ag"
                    + "WHERE"
                    + "mansione = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, mansione);

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

    addettoGiostre read(ResultSet rs){
        addettoGiostre ag = new addettoGiostre();
        try{
            ag.setCodice_fiscale(rs.getString("codice_fiscale"));
        }catch (SQLException sqle){
        }
        try{
            ag.setMansione(rs.getString("mansione"));
        }catch (SQLException sqle){
        }
        try{
            ag.setCodice_giostra(rs.getLong("codice_giostra"));
        }catch (SQLException sqle){
        }
        return ag;
    }
}
