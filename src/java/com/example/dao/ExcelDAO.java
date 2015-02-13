package com.example.dao;

import com.example.model.Estudiante;
import java.util.List;

public interface ExcelDAO  extends AutoCloseable {
    public void create(Estudiante est) throws DAOException;//CREATE
    public Estudiante findByCedula(int cedula) throws DAOException;//READ
    public void update(Estudiante est) throws DAOException;//UPDATE
    public void delete(int cedula) throws DAOException;//DELETE
    public List<Estudiante> getAllEstudiantes() throws DAOException;
}
