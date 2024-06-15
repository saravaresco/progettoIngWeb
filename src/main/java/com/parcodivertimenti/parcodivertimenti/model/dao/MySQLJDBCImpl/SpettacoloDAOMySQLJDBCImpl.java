package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.spettacolo;
import com.parcodivertimenti.parcodivertimenti.model.dao.spettacoloDAO;

import java.sql.*;
import java.util.Date;

public class SpettacoloDAOMySQLJDBCImpl implements spettacoloDAO {

    private final String COUNTER_ID = "spettacoloId";
    Connection conn;

    public SpettacoloDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public spettacolo create(String nome, String tipoloigia, Date data, String luogo, Time orario_inizio, Time durata){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(spettacolo spett){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(spettacolo spett){
        throw new UnsupportedOperationException("Not supported yet");
    }


    @Override
    public spettacolo findByNome(String nome){
        PreparedStatement ps;
        spettacolo s = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM s"
                    + "WHERE"
                    + "nome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,nome);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                s = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return s;
    }

    @Override
    public spettacolo findByTipologia(String tipologia){
        PreparedStatement ps;
        spettacolo s = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM s"
                    + "WHERE"
                    + "tipologia = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, tipologia);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                s = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return s;
    }

    @Override
    public spettacolo findByData(Date data){
        PreparedStatement ps;
        spettacolo s = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM s"
                    + "WHERE"
                    + "data = ?";

            ps = conn.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) data);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                s = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return s;
    }

    @Override
    public spettacolo findByLuogo(String luogo){
        PreparedStatement ps;
        spettacolo s = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM s"
                    + "WHERE"
                    + "luogo = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, luogo);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                s = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return s;
    }

    @Override
    public spettacolo findByOrarioI(Time orario_inizio){
        PreparedStatement ps;
        spettacolo s = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM s"
                    + "WHERE"
                    + "orario_inizio = ?";

            ps = conn.prepareStatement(sql);
            ps.setTime(1, orario_inizio);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                s = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return s;
    }

    spettacolo read(ResultSet rs){
        spettacolo s = new spettacolo();
        try{
            s.setNome(rs.getString("nome"));
        }catch (SQLException sqle){
        }
        try{
            s.setTipologia(rs.getString("tipologia"));
        }catch (SQLException sqle){
        }
        try{
            s.setData(rs.getDate("data"));
        }catch (SQLException sqle){
        }
        try{
            s.setLuogo(rs.getString("luogo"));
        }catch (SQLException sqle){
        }
        try{
            s.setOrario_inizio(rs.getTime("orario_inizio"));
        }catch (SQLException sqle){
        }
        try{
            s.setDurata(rs.getTime("durata"));
        }catch (SQLException sqle){
        }

        return s;
    }
}
