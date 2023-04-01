package com.ejercicio2.ApiRestFullFarmacia.response;

import com.ejercicio2.ApiRestFullFarmacia.model.Medicamento;

import java.util.List;

public class MedicamentoResponse {
    private List<Medicamento> medicamentos;
    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
