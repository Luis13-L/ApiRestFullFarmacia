package com.ejercicio2.ApiRestFullFarmacia.response;

import com.ejercicio2.ApiRestFullFarmacia.model.Categoria;

import java.util.List;

public class CategoriaResponse {
    private List<Categoria> categorias;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
