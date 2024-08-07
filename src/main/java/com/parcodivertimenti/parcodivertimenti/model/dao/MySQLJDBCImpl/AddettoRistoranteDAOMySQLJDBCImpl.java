package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.dao.addettoRistoranteDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.addettoRistorante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.module.ModuleDescriptor.read;

public class AddettoRistoranteDAOMySQLJDBCImpl implements addettoRistoranteDAO{

    private final String COUNTER_ID = "addettoRistoranteId";
    Connection conn;

    public AddettoRistoranteDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public addettoRistorante create(String codice_fiscale,
                                    String posizione,
                                    Long ID_punto_ristoro,
                                    String username,
                                    String password){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(addettoRistorante addettoristorante){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(addettoRistorante addettoristorante){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public addettoRistorante findLoggedUser(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public addettoRistorante findByUserCF(String codice_fiscale){
        PreparedStatement ps;
        addettoRistorante ad = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM ad"
                    + "WHERE"
                    + "codice_fiscale = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,codice_fiscale);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                ad = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return ad;
    }

    @Override
    public addettoRistorante findByPuntoRistoro(Long ID_punto_ristoro){
        PreparedStatement ps;
        addettoRistorante ad = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM ag"
                    + "WHERE"
                    + "ID_punto_ristoro = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, ID_punto_ristoro);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                ad = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return ad;
    }

    @Override
    public addettoRistorante findByUsername(String username) {

        PreparedStatement ps;
        addettoRistorante ar = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM ar "
                    + " WHERE "
                    + "   username = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                ar = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ar;

    }


    addettoRistorante read(ResultSet rs){
        addettoRistorante ad = new addettoRistorante();
        try{
            ad.setCodice_fiscale(rs.getString("CODICE_FISCALE"));
        }catch (SQLException sqle){
        }
        try{
            ad.setPosizione(rs.getString("POSIZIONE"));
        }catch (SQLException sqle){
        }
        try{
            ad.setID_punto_ristoro(rs.getLong("ID_PUNTO_RISTORO"));
        }catch (SQLException sqle){
        }
        try{
            ad.setUsername(rs.getString("USERNAME"));
        }catch (SQLException sqle){
        }
        try{
            ad.setPassword(rs.getString("PASSWORD"));
        }catch (SQLException sqle){
        }
        return ad;
    }
}
