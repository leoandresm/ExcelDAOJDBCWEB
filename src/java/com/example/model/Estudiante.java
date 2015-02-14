package com.example.model;

import java.util.Objects;

public class Estudiante {
    private long cedula;
    private String apellidos;
    private String nombres;
    private int ficha;
    private int codProf;

    public Estudiante() {
    }
    
    public Estudiante(long cedula, String apellidos, String nombres, int ficha, int codProf) {
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.ficha = ficha;
        this.codProf = codProf;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }

    public long getCedula() {
        return cedula;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public int getFicha() {
        return ficha;
    }

    public int getCodProf() {
        return codProf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.cedula ^ (this.cedula >>> 32));
        hash = 59 * hash + Objects.hashCode(this.apellidos);
        hash = 59 * hash + Objects.hashCode(this.nombres);
        hash = 59 * hash + this.ficha;
        hash = 59 * hash + this.codProf;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estudiante other = (Estudiante) obj;
        if (this.cedula != other.cedula) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.nombres, other.nombres)) {
            return false;
        }
        if (this.ficha != other.ficha) {
            return false;
        }
        if (this.codProf != other.codProf) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "cedula=" + cedula + ", apellidos=" + apellidos + ", nombres=" + nombres + ", ficha=" + ficha + ", codProf=" + codProf + '}';
    }      
}
