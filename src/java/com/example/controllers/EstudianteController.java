/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.dao.EstudianteDAOFactory;
import com.example.dao.ExcelDAO;
import com.example.model.Estudiante;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author LeoAndresM
 */
@ManagedBean
@SessionScoped
public class EstudianteController {

    private Estudiante estudianteActual;
    private List<Estudiante> listaEstudiantes = null;

    private EstudianteDAOFactory factory;

    /**
     * Creates a new instance of EstudianteController
     */
    public EstudianteController() {
        factory = new EstudianteDAOFactory();
    }

    public Estudiante getEstudianteActual() {
        if (estudianteActual == null) {
            estudianteActual = new Estudiante();
        }
        return estudianteActual;
    }

    public void setEstudianteActual(Estudiante estudianteActual) {
        this.estudianteActual = estudianteActual;
    }

    public List<Estudiante> getListaEstudiantes() {
        if (listaEstudiantes == null) {
            try (ExcelDAO dao = factory.createEstudianteDAO()) {
                listaEstudiantes = dao.getAllEstudiantes();
            } catch (Exception ex) {
                System.out.println("Error consultando estudiantes");
            }
        }
        return listaEstudiantes;
    }

    public void save() {
        if (estudianteActual != null) {
            try (ExcelDAO dao = factory.createEstudianteDAO()) {
                if (dao.findByCedula(estudianteActual.getCedula()) == null) {
                    dao.create(estudianteActual);
                    estudianteActual = null;
                    listaEstudiantes = null;
                } else {
                    dao.update(estudianteActual);
                    estudianteActual = null;
                    listaEstudiantes = null;
                }

            } catch (Exception ex) {
                System.out.println("Error creando estudiante");
            }
        }
    }

}
