package com.ejercicio2.ApiRestFullFarmacia.controller;

import com.ejercicio2.ApiRestFullFarmacia.model.Medicamento;
import com.ejercicio2.ApiRestFullFarmacia.response.MedicamentoResponseRest;
import com.ejercicio2.ApiRestFullFarmacia.service.IMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api3/v1")
@CrossOrigin(origins = "4000")

public class MedicamentoRestController {

    @Autowired
    private IMedicamentoService medicamentoService;


    @GetMapping("/medicamentos")
    public ResponseEntity<MedicamentoResponseRest> buscarMedicamentos() {
        return medicamentoService.buscarMedicamentos(); // Retornamos la respuesta al cliente
    }

    @GetMapping("/medicamentos/{id}")
    public ResponseEntity<MedicamentoResponseRest> consultarPorId(@PathVariable Long id) {
        return medicamentoService.buscarMedicamentoPorId(id);
    }

    @PostMapping("/medicamentos")
    public ResponseEntity<MedicamentoResponseRest> guardarMedicamento(@RequestBody Medicamento request) {
        return medicamentoService.crear(request);
    }

    @PutMapping("/medicamentos/{id}")
    public ResponseEntity<MedicamentoResponseRest> actualizarMedicamento(@RequestBody Medicamento request, @PathVariable Long id) {
        return medicamentoService.actualizar(request,id);

    }

    @DeleteMapping("/medicamentos/{id}")
    public ResponseEntity<MedicamentoResponseRest> eliminarMedicamento(@PathVariable Long id) {
        return medicamentoService.eliminar(id);
    }

}
