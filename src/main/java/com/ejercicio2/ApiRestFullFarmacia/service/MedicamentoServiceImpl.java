package com.ejercicio2.ApiRestFullFarmacia.service;

import com.ejercicio2.ApiRestFullFarmacia.model.Medicamento;
import com.ejercicio2.ApiRestFullFarmacia.model.dao.IMedicamentoDao;
import com.ejercicio2.ApiRestFullFarmacia.response.MedicamentoResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService{
    private static final Logger log = Logger.getLogger(CategoriaServiceImpl.class.getName());

    @Autowired
    private IMedicamentoDao medicamentoDao;


    @Override
    @Transactional(readOnly = true) // @Transactional: indica que este metodo se ejecutara dentro de una transaccion
    public ResponseEntity<MedicamentoResponseRest> buscarMedicamentos() {
        log.info("Buscando todos los medicamentos");

        MedicamentoResponseRest response = new MedicamentoResponseRest();

        try{
            List<Medicamento> listMedicamentos = (List<Medicamento>) medicamentoDao.findAll();
            response.getMedicamentoResponse().setMedicamentos(listMedicamentos);
            response.setMetadata("Respuesta exitosa", "200", "Lista de medicamentos");

        }catch (Exception e){
            log.severe("Error al buscar los medicamentos: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al buscar los medicamentos", "500", e.getMessage());
            return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200


    }

    @Override
    @Transactional(readOnly = true) // @Transactional: indica que este metodo se ejecutara dentro de una transaccion
    public ResponseEntity<MedicamentoResponseRest> buscarMedicamentoPorId(Long id) {
        log.info("Iniciando metodo buscarPorId () ");

        MedicamentoResponseRest response = new MedicamentoResponseRest();
        List<Medicamento> list = new ArrayList<>();
        try {
            Optional<Medicamento> medicamento = medicamentoDao.findById(id);

            if(medicamento.isPresent()) {
                list.add(medicamento.get());
                response.getMedicamentoResponse().setMedicamentos(list);
            }else{
                log.severe("No se encontro el medicamento con el id: " + id);
                response.setMetadata("No se encontro el medicamento", "404", "No se encontro el medicamento con el id: " + id);
                return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.NOT_FOUND); // Retornamos la respuesta con el codigo de error 404
            }

        }catch (Exception e){
            log.severe("Error al buscar el medicamento: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al buscar el medicamento", "500","Error al consultar el medicamento");
            return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500
        }

        response.setMetadata("Respuesta exitosa", "200", "medicamento encontrado"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200

    }

    @Override
    @Transactional // @Transactional: indica que este metodo se ejecutara dentro de una transaccion
    public ResponseEntity<MedicamentoResponseRest> crear(Medicamento medicamento) {
        log.info("Iniciando metodo crear () ");

        MedicamentoResponseRest response = new MedicamentoResponseRest();
        List<Medicamento> list = new ArrayList<>();

        try{
            Medicamento medicamentoGuardado = medicamentoDao.save(medicamento); // Guardamos el medicamento en la base de datos

            if(medicamento!=null) { // Si el medicamento se guardo correctamente
                list.add(medicamentoGuardado); // Agregamos el medicamento a la lista
                response.getMedicamentoResponse().setMedicamentos(list); // Guardamos la lista de medicamentos en la respuesta
            }else {
                log.severe("Error al guardar el medicamento");
                response.setMetadata("Error al guardar el medicamento", "500", "Error al guardar el medicamento"); // Guardamos los metadatos de la respuesta
                return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500
            }

        }catch (Exception e){
            log.severe("Error al guardar el medicamento: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al guardar el medicamento", "500", "Error al Guaradr el medicamento"); // Guardamos los metadatos de la respuesta
            return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500

        }

        response.setMetadata("Respuesta exitosa", "200", "medicamento creado"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200

    }

    @Override
    @Transactional
    public ResponseEntity<MedicamentoResponseRest> actualizar(Medicamento medicamento, Long id) {
        log.info("Iniciando metodo actualizar () ");

        MedicamentoResponseRest response = new MedicamentoResponseRest();
        List<Medicamento> list = new ArrayList<>();

        try{
            Optional<Medicamento> medicamentoBuscado = medicamentoDao.findById(id); // Buscamos el medicamento por su id

            if(medicamentoBuscado.isPresent()){
                medicamentoBuscado.get().setNombre(medicamento.getNombre());
                medicamentoBuscado.get().setDescripcion(medicamento.getDescripcion());
                medicamentoBuscado.get().setCategoria(medicamento.getCategoria());

                Medicamento medicamentoActualizado = medicamentoDao.save(medicamentoBuscado.get());

                if(medicamentoActualizado!=null) {
                    list.add(medicamentoActualizado);
                    response.getMedicamentoResponse().setMedicamentos(list);
                }else {
                    log.severe("Error al actualizar el medicamento");
                    response.setMetadata("Error al actualizar el medicamento", "500", "Error al actualizar el medicamento"); // Guardamos los metadatos de la respuesta
                    return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.BAD_REQUEST); // Retornamos la respuesta con el codigo de error 500
                }
            }else{
                log.severe("No se encontro el medicamento con el id: " + id);
                response.setMetadata("No se encontro el medicamento", "404", "No se encontro el medicamento con el id: " + id); // Guardamos los metadatos de la respuesta
                return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.NOT_FOUND); // Retornamos la respuesta con el codigo de error 404
            }

        }catch (Exception e){
            log.severe("Error al actualizar el medicamento: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al actualizar el medicamento", "500", "Error al actualizar el medicamento"); // Guardamos los metadatos de la respuesta
            return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500


        }

        response.setMetadata("Respuesta exitosa", "200", "medicamento actualizado"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200

    }

    @Override
    @Transactional
    public ResponseEntity<MedicamentoResponseRest> eliminar(Long id) {
        log.info("Iniciando metodo eliminar () ");

        MedicamentoResponseRest response = new MedicamentoResponseRest(); // Creamos un objeto de tipo LibroResponseRest para guardar la respuesta que se le dara al cliente

        try{
            Optional<Medicamento> medicamentoBuscado = medicamentoDao.findById(id); // Buscamos el medicamento por su id

            if(medicamentoBuscado.isPresent()){
                medicamentoDao.delete(medicamentoBuscado.get()); // Eliminamos el medicamento de la base de datos
            }else{
                log.severe("No se encontro el medicamento con el id: " + id);
                response.setMetadata("No se encontro el medicamento", "404", "No se encontro el medicamento con el id: " + id); // Guardamos los metadatos de la respuesta
                return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.NOT_FOUND); // Retornamos la respuesta con el codigo de error 404
            }


        }catch (Exception e){

            log.severe("Error al eliminar el medicamento: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al eliminar el medicamento", "500", "Error al eliminar el medicamento"); // Guardamos los metadatos de la respuesta
            return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500
        }

        response.setMetadata("Respuesta exitosa", "200", "medicamento eliminado"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<MedicamentoResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200
    }
}
