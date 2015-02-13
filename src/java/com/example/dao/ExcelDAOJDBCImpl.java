/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.model.Estudiante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExcelDAOJDBCImpl implements ExcelDAO {

    private Connection conexion = null;

    ExcelDAOJDBCImpl() {
        String url = "jdbc:mysql://localhost:3306/excel";
        String username = "root";
        String password = "1234";
        try {
            conexion = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error obteniendo la conexión: " + e);
            System.exit(-1);
        }
    }

    @Override
    public void create(Estudiante est) throws DAOException {
        try (Statement stmt = conexion.createStatement()) {
            String query = "INSERT INTO estudiante "
                    + "VALUES(" + est.getCedula() + ", '"
                    + est.getApellidos() + "', "
                    + "'" + est.getNombres() + "',"
                    + est.getFicha() + ","
                    + est.getCodProf() + ");";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            throw new DAOException("Error adicionando "
                    + "estudiante en el DAO", ex);
        }
    }

    @Override
    public Estudiante findByCedula(int cedula) throws DAOException {
        try (Statement stmt = conexion.createStatement()) {
            String query = "SELECT * FROM estudiante WHERE cedula="+ cedula;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Estudiante(rs.getInt("cedula"),
                    rs.getString("apellidos"),
                    rs.getString("nombres"),
                    rs.getInt("ficha"),
                    rs.getInt("cod_prof")));
        } catch (SQLException ex) {
            throw new DAOException("Error buscando "
                    + "estudiante en DAO", ex);
        }
    }

    @Override
    public void update(Estudiante est) throws DAOException {
        try (Statement stmt = conexion.createStatement()) {
            String query = "UPDATE estudiante "
                    + "SET apellidos='" + est.getApellidos() + "',"
                    + "nombres='" + est.getNombres() + "',"
                    + "ficha=" + est.getFicha() + ","
                    + "cod_prof=" + est.getCodProf() 
                    + " WHERE cedula="+ est.getCedula();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            throw new DAOException("Error actualizando "
                    + "estudiante en el DAO", ex);
        }
    }

    @Override
    public void delete(int cedula) throws DAOException {
        try (Statement stmt = conexion.createStatement()) {
            String query = "DELETE FROM estudiante WHERE cedula=" + cedula;
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            throw new DAOException("Error actualizando "
                    + "estudiante en el DAO", ex);
        }
    }

    @Override
    public List<Estudiante> getAllEstudiantes() throws DAOException {
        try (Statement stmt = conexion.createStatement()) {
            String query = "SELECT * FROM estudiante";
            ResultSet rs = stmt.executeQuery(query);
            List<Estudiante> ests = new ArrayList<>();
            while (rs.next()) {
               ests.add(new Estudiante(rs.getLong("cedula"),
                    rs.getString("apellidos"),
                    rs.getString("nombres"),
                    rs.getInt("ficha"),
                    rs.getInt("cod_prof")));                
            }
            return ests;
        } catch (SQLException ex) {
            throw  new DAOException("Error obteniendo todos "
                    + "los estudiantes", ex);
        }
    }

    @Override
    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error cerrando la conexión");
        }
    }

}
