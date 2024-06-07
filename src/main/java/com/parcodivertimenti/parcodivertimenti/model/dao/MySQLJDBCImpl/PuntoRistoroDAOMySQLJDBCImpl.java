package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.puntoRistoro;

import java.sql.*;
import java.util.Date;

public class PuntoRistoroDAOMySQLJDBCImpl {

    private final String COUNTER_ID = "puntoRistoroId";
    Connection conn;

    public PuntoRistoroDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public puntoRistoro create(Long ID, String nome, String tipo_cucina, Time orario_apertura, Time orario_chiusura){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(puntoRistoro puntoristoro){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(puntoRistoro puntoristoro){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public puntoRistoro findLoggedUser(){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public puntoRistoro findById(Long ID){
        PreparedStatement ps;
        puntoRistoro pr = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM pr"
                    + "WHERE"
                    + "ID = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1,ID);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                pr = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return pr;
    }

    @Override
    public puntoRistoro findByNome(String nome){
        PreparedStatement ps;
        puntoRistoro pr = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM pr"
                    + "WHERE"
                    + "nome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                pr = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return pr;
    }

    @Override
    public puntoRistoro findByTipoCucina(String tipo_cucina){
        PreparedStatement ps;
        puntoRistoro pr = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM pr"
                    + "WHERE"
                    + "tipo_cucina = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, tipo_cucina);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                pr = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return pr;
    }

    @Override
    public puntoRistoro findByOrarioA(Time orario_apertura){
        PreparedStatement ps;
        puntoRistoro pr = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM pr"
                    + "WHERE"
                    + "orario_apertura = ?";

            ps = conn.prepareStatement(sql);
            ps.setTime(1, orario_apertura);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                pr = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return pr;
    }

    @Override
    public puntoRistoro findByOrarioC(Time orario_chiusura){
        PreparedStatement ps;
        puntoRistoro pr = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM pr"
                    + "WHERE"
                    + "orario_chiusura = ?";

            ps = conn.prepareStatement(sql);
            ps.setTime(1, orario_chiusura);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                pr = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return pr;
    }

    puntoRistoro read(ResultSet rs){
        puntoRistoro at = new puntoRistoro();
        try{
            at.setID(rs.getLong("ID"));
        }catch (SQLException sqle){
        }
        try{
            at.setNome(rs.getString("nome"));
        }catch (SQLException sqle){
        }
        try{
            at.setTipo_cucina(rs.getString("tipo_cucina"));
        }catch (SQLException sqle){
        }
        try{
            at.setOrario_apertura(rs.getTime("orario_apertura"));
        }catch (SQLException sqle){
        }
        try{
            at.setOrario_chiusura(rs.getTime("orario_chiusura"));
        }catch (SQLException sqle){
        }

        return at;
    }
}
