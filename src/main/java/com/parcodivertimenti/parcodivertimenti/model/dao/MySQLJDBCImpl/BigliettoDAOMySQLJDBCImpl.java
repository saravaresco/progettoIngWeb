package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.mo.biglietto;
import com.parcodivertimenti.parcodivertimenti.model.dao.bigliettoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BigliettoDAOMySQLJDBCImpl implements bigliettoDAO {

    private final String COUNTER_ID = "bigliettoId";
    Connection conn;

    public BigliettoDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override
    public biglietto create(Long ID, String codice_fiscale, Long prezzo, Date data_acquisto, String tipoligia){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(biglietto bigl){
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void delete(biglietto bigl){
        throw new UnsupportedOperationException("Not supported yet");
    }


    @Override
    public biglietto findById(Long ID){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM at"
                    + "WHERE"
                    + "ID = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1,ID);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    @Override
    public biglietto findByCF(String codice_fiscale){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM b"
                    + "WHERE"
                    + "codice_fiscale = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, codice_fiscale);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    @Override
    public biglietto findByData(Date data_acquisto){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM b"
                    + "WHERE"
                    + "data_acquisto = ?";

            ps = conn.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) data_acquisto);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    @Override
    public biglietto findByTipologia(String tipologia){
        PreparedStatement ps;
        biglietto b = null;

        try{
            String sql
                    = "SELECT *"
                    + "FROM b"
                    + "WHERE"
                    + "tipologia = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, tipologia);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                b = read(resultSet);
            }
            resultSet.close();
            ps.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return b;
    }

    biglietto read(ResultSet rs){
        biglietto b = new biglietto();
        try{
            b.setID(rs.getLong("ID"));
        }catch (SQLException sqle){
        }
        try{
            b.setCodice_fiscale(rs.getString("codice_fiscale"));
        }catch (SQLException sqle){
        }
        try{
            b.setPrezzo(rs.getLong("prezzo"));
        }catch (SQLException sqle){
        }
        try{
            b.setData_acquisto(rs.getDate("data_acquisto"));
        }catch (SQLException sqle){
        }

        try{
            b.setTipologia(rs.getString("tipologia"));
        }catch (SQLException sqle){
        }

        return b;
    }
}
