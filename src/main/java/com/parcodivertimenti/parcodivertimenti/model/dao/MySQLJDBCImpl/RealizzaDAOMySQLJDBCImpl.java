package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.dao.realizzaDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.realizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RealizzaDAOMySQLJDBCImpl implements realizzaDAO {
    private final String COUNTER_ID = "attoreId";
    Connection conn;

    public RealizzaDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override /*da implementare se sito prevede di potersi registrare (stessa cosa per update e delete)*/
    public realizza create(String cf_attore, String nome_spettacolo){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(realizza Realizza){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(realizza Realizza){
        throw new UnsupportedOperationException("Not supported yet");
    }


    @Override
    public List<realizza> findByCFAttore(String cf_attore){
        List<realizza> realizza = new ArrayList<>();
        String sql = "SELECT * FROM realizza WHERE CF_ATTORE = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setString(1, cf_attore);
             try(ResultSet rs = ps.executeQuery()){
                 while(rs.next()){
                     realizza rip = new realizza();
                     rip.setNome_spettacolo(rs.getString("nome_spettacolo"));
                     realizza.add(rip);
                 }
             }
        } catch (SQLException e){
            throw new RuntimeException("Errore durante il recupero dei biglietti", e);
        }

        return realizza;

    }

    @Override
    public realizza findByNomeSpettacolo(String nome_spettacolo){
        PreparedStatement ps;
        realizza ag = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM ag"
                    + "WHERE"
                    + "nome_spettacolo = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nome_spettacolo);

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


    realizza read(ResultSet rs){
        realizza ag = new realizza();
        try{
            ag.setCf_attore(rs.getString("cf_attore"));
        }catch (SQLException sqle){
        }
        try{
            ag.setNome_spettacolo(rs.getString("nomeSpettacolo"));
        }catch (SQLException sqle){
        }


        return ag;
    }


}
