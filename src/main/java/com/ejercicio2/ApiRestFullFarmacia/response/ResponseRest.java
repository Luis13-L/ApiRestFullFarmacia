package com.ejercicio2.ApiRestFullFarmacia.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>(); // clave: valor

    public ArrayList<HashMap<String, String>> getMetadata() {
        // getMetadata: obtiene la información de la respuesta.
        return metadata;
    }

    public void setMetadata(String tipo, String codigo, String dato) {
        // setMetadata: establece la información de la respuesta.
        HashMap<String, String> mapa = new HashMap<String,String>();
        mapa.put("tipo", tipo); // tipo: tipo de respuesta.
        mapa.put("codigo", codigo);
        mapa.put("dato", dato);
        metadata.add(mapa);
    }
}
