package com.ejercicio2.ApiRestFullFarmacia.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categoria") // nombre de la tabla en la base de datos de JPA
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L; // identificador unico de la clase autogenerado
    @Id // jakarta.persistence java persistence api (JPA) primera opcion
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
