package com.ejercicio2.ApiRestFullFarmacia.model.dao;

import com.ejercicio2.ApiRestFullFarmacia.model.Medicamento;
import org.springframework.data.repository.CrudRepository;

public interface IMedicamentoDao extends CrudRepository<Medicamento, Long> {

}
