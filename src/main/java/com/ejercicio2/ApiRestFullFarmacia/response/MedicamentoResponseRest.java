package com.ejercicio2.ApiRestFullFarmacia.response;

public class MedicamentoResponseRest extends ResponseRest{
    private MedicamentoResponse medicamentoResponse = new MedicamentoResponse();

    public MedicamentoResponse getMedicamentoResponse() {
        return medicamentoResponse;
    }

    public void setMedicamentoResponse(MedicamentoResponse medicamentoResponse) {
        this.medicamentoResponse = medicamentoResponse;
    }
}
