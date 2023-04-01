package com.ejercicio2.ApiRestFullFarmacia.service;

import com.ejercicio2.ApiRestFullFarmacia.model.Medicamento;
import com.ejercicio2.ApiRestFullFarmacia.response.MedicamentoResponseRest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface IMedicamentoService {
    public ResponseEntity<MedicamentoResponseRest> buscarMedicamentos();

    //buscar por id
    public ResponseEntity<MedicamentoResponseRest> buscarMedicamentoPorId(Long id);

    //guardar un medicamento
    public ResponseEntity<MedicamentoResponseRest> crear(Medicamento medicamento);

    //actualizar un medicamento
    public ResponseEntity<MedicamentoResponseRest> actualizar(Medicamento medicamento, Long id);

    //eliminar un medicamento
    public ResponseEntity<MedicamentoResponseRest> eliminar(Long id);

}
