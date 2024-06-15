package com.parcodivertimenti.parcodivertimenti.model.dao.MySQLJDBCImpl;

import com.parcodivertimenti.parcodivertimenti.model.dao.exception.DuplicatedObjectException;
import com.parcodivertimenti.parcodivertimenti.model.dao.visitatoreDAO;
import com.parcodivertimenti.parcodivertimenti.model.mo.visitatore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class VisitatoreDAOMySQLJDBCImpl implements visitatoreDAO {

    private final String COUNTER_ID = "visitatoreId";
    Connection conn;

    public VisitatoreDAOMySQLJDBCImpl(Connection conn){this.conn = conn;}

    @Override /*in questo caso lo implemento percè l'utente visitatore posso crearlo*/
    public visitatore create(String codice_fiscale, String nome, String cognome, Date data_nascita, String sesso, String username, String password) {
        PreparedStatement ps;
        visitatore visitatore = new visitatore();
        visitatore.setCodice_fiscale(codice_fiscale);
        visitatore.setNome(nome);
        visitatore.setCognome(cognome);
        visitatore.setData_nascita(data_nascita);
        visitatore.setSesso(sesso);
        visitatore.setUsername(username);
        visitatore.setPassword(password);
        /*verifico se visitaore è già presente*/
        try{
            String sql
                    = "SELECT visitatoreId"
                    + "FROM visitatore"
                    + "WHERE "
                    + "codice_fiscale = ? AND"
                    + "nome = ? AND"
                    + "cognome = ?";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, visitatore.getCodice_fiscale());
            ps.setString(i++, visitatore.getNome());
            ps.setString(i++, visitatore.getCognome());


            ResultSet resultSet = ps.executeQuery();

            boolean exist;
            exist = resultSet.next();
            resultSet.close();

            if(exist){
                throw new DuplicatedObjectException("VisitatoreDaoJDBCImpl.create: Tentativo di inserimento di un visitatore già esistente");
            }

            sql = "update counter set counterValue=counterValue+1 where counterId='" + COUNTER_ID +"'";

            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            sql = "SELECT counterValue FROM counter where counterId='" + COUNTER_ID + "'";

            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            resultSet.next();

            visitatore.setCodice_fiscale(resultSet.getString("codice_fiscale"));

            resultSet.close();

            sql
                    = " INSERT INTO visitatore "
                    + "   ( codice_fiscale,"
                    + "     nome,"
                    + "     cognome,"
                    + "     data_nascita,"
                    + "     sesso,"
                    + "     username,"
                    + "     password,"
                    + "   ) "
                    + " VALUES (?,?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            i = 1;
            ps.setString(i++, visitatore.getCodice_fiscale());
            ps.setString(i++, visitatore.getNome());
            ps.setString(i++, visitatore.getCognome());
            ps.setDate(i++, visitatore.getData_nascita());
            ps.setString(i++, visitatore.getSesso());
            ps.setString(i++, visitatore.getUsername());
            ps.setString(i++, visitatore.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DuplicatedObjectException e) {
            throw new RuntimeException(e);
        }


        return visitatore;
    }

    @Override
    public void update(visitatore visit) {

        PreparedStatement ps;

        try {

            String sql
                    = " SELECT codice_fiscale "
                    + " FROM visitatore "
                    + " WHERE "
                    + " nome =? AND "
                    + " cognome = ? AND"
                    + " data_nascita = ? AND"
                    + " codice_fiscale <> ?";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, visit.getNome());
            ps.setString(i++, visit.getCognome());
            ps.setDate(i++, visit.getData_nascita());
            ps.setString(i++, visit.getCodice_fiscale());

            ResultSet resultSet = ps.executeQuery();

            boolean exist;
            exist = resultSet.next();
            resultSet.close();

            if (exist) {
                throw new DuplicatedObjectException("VisitatoreDAOMyJDBCImpl.create: Tentativo di aggiornamento in un visitatore già esistente.");
            }

            sql
                    = " UPDATE visitatore "
                    + " SET "
                    + "   nome = ?, "
                    + "   cognome = ?, "
                    + "   data_nascita = ?, "
                    + "   sesso = ?, "
                    + "   username = ?, "
                    + "   password = ?, "
                    + " WHERE "
                    + "   codice_fiscale= ? ";

            ps = conn.prepareStatement(sql);
            i = 1;
            ps.setString(i++,visit.getNome());
            ps.setString(i++, visit.getCognome());
            ps.setDate(i++, visit.getData_nascita());
            ps.setString(i++, visit.getSesso());
            ps.setString(i++, visit.getUsername());
            ps.setString(i++, visit.getPassword());
            ps.setString(i++, visit.getCodice_fiscale());
            ps.executeUpdate();

        } catch (SQLException | DuplicatedObjectException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(visitatore visit){
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE visitatore "
                    + " WHERE "
                    + " codice_fiscale=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, visit.getCodice_fiscale());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try{
            v.setUsername(rs.getString("username"));
        }catch (SQLException sqle){
        }
        try{
            v.setPassword(rs.getString("password"));
        }catch (SQLException sqle){
        }

        return v;
    }
}
