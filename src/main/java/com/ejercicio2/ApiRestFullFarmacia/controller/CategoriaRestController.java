package com.ejercicio2.ApiRestFullFarmacia.controller;

import com.ejercicio2.ApiRestFullFarmacia.model.Categoria;
import com.ejercicio2.ApiRestFullFarmacia.response.CategoriaResponseRest;
import com.ejercicio2.ApiRestFullFarmacia.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api3/v1")
public class CategoriaRestController{

    @Autowired
    private ICategoriaService categoriaService;
    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultarCategorias(){
        return categoriaService.buscarCategorias();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultarCategoriaId(@PathVariable Long id){
        return categoriaService.buscarCategoriaId(id);
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request){
        return categoriaService.crear(request);
    }
    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> actualizar(@PathVariable Long id, @RequestBody Categoria request){
        return categoriaService.actualizar(id,request);
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> eliminar(@PathVariable Long id){

        return categoriaService.eliminar(id);
    }
}
