package com.example.dao;

/**
 *
 * @author LeoAndresM
 */
public class EstudianteDAOFactory {
    public ExcelDAO createEstudianteDAO() {
        return new ExcelDAOJDBCImpl();
    }
}
