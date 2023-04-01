package com.ejercicio2.ApiRestFullFarmacia.model.dao;

import com.ejercicio2.ApiRestFullFarmacia.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {
}
