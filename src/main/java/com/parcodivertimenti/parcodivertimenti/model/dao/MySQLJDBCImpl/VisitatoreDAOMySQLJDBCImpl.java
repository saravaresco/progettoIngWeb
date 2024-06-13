package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.addettoGiostre;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class VisitatoreDAOMySQLJDBCImpl {

    private final String COUNTER_ID = "visitatoreId";
    Connection conn;

    public VisitatoreDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public visitatore create(String codice_fiscale, String nome, String cognome, Date data_nascita, String sesso){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(visitatore visit){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(visitatore visit){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public visitatore findLoggedUser(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public visitatore findByCF(String codice_fiscale){
        PreparedStatement ps;
        visitatore v = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM v"
                    + "WHERE"
                    + "codice_fiscale = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,codice_fiscale);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                v = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return v;
    }

    @Override
    public visitatore findByNome(String nome){
        PreparedStatement ps;
        visitatore v = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM v"
                    + "WHERE"
                    + "nome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                v = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return v;
    }

    @Override
    public visitatore findByCognome(String cognome){
        PreparedStatement ps;
        visitatore v = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM v"
                    + "WHERE"
                    + "cognome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cognome);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                v = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return v;
    }

    @Override
    public visitatore findByDataNascita(Date data_nascita){
        PreparedStatement ps;
        visitatore v = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM v"
                    + "WHERE"
                    + "data_nascita = ?";

            ps = conn.prepareStatement(sql);
            ps.setDate(1, data_nascita);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                v = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return v;
    }

    @Override
    public visitatore findByUsername(String username) {

        PreparedStatement ps;
        visitatore v = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM v "
                    + " WHERE "
                    + "   username = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                v = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return v;

    }

    visitatore read(ResultSet rs){
        visitatore v = new visitatore();
        try{
            v.setCodice_fiscale(rs.getString("codice_fiscale"));
        }catch (SQLException sqle){
        }
        try{
            v.setNome(rs.getString("nome"));
        }catch (SQLException sqle){
        }
        try{
            v.setCognome(rs.getString("cognome"));
        }catch (SQLException sqle){
        }
        try{
            v.setData_nascita(rs.getDate("data_nascita"));
        }catch (SQLException sqle){
        }
        try{
            v.setSesso(rs.getString("sesso"));
        }catch (SQLException sqle){
        }

        return v;
    }
}
