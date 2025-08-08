package com.modista.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cliente {
    private int id;
    private String nombre;
    private double busto;
    private double cintura;
    private double cadera;
    private String talla;
    private LocalDateTime fechaRegistro;

    public Cliente(String nombre, double busto, double cintura, double cadera) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (busto <= 0 || cintura <= 0 || cadera <= 0) {
            throw new IllegalArgumentException("Las medidas deben ser mayores que 0");
        }
        
        this.nombre = nombre.trim();
        this.busto = busto;
        this.cintura = cintura;
        this.cadera = cadera;
        this.fechaRegistro = LocalDateTime.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }
    
    public double getBusto() { return busto; }
    public void setBusto(double busto) {
        if (busto <= 0) {
            throw new IllegalArgumentException("El busto debe ser mayor que 0");
        }
        this.busto = busto;
    }
    
    public double getCintura() { return cintura; }
    public void setCintura(double cintura) {
        if (cintura <= 0) {
            throw new IllegalArgumentException("La cintura debe ser mayor que 0");
        }
        this.cintura = cintura;
    }
    
    public double getCadera() { return cadera; }
    public void setCadera(double cadera) {
        if (cadera <= 0) {
            throw new IllegalArgumentException("La cadera debe ser mayor que 0");
        }
        this.cadera = cadera;
    }
    
    public String getTalla() { return talla; }
    public void setTalla(String talla) {
        if (talla == null || talla.trim().isEmpty()) {
            throw new IllegalArgumentException("La talla no puede estar vacía");
        }
        this.talla = talla.trim();
    }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { 
        this.fechaRegistro = fechaRegistro; 
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", busto=" + busto +
                ", cintura=" + cintura +
                ", cadera=" + cadera +
                ", talla='" + talla + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id &&
                Double.compare(cliente.busto, busto) == 0 &&
                Double.compare(cliente.cintura, cintura) == 0 &&
                Double.compare(cliente.cadera, cadera) == 0 &&
                Objects.equals(nombre, cliente.nombre) &&
                Objects.equals(talla, cliente.talla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, busto, cintura, cadera, talla);
    }
}
