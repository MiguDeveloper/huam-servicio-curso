package pe.tuna.microserviciocurso.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.commonsmicroservicios.controllers.CommonController;
import pe.tuna.microserviciocurso.entity.Curso;
import pe.tuna.microserviciocurso.service.ICursoService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CursoController extends CommonController<Curso, ICursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurso(@RequestBody Curso curso, @PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        Curso cursoBd;
        Curso cursoUpdate;
        try {
            cursoBd = servicio.findById(id);
            cursoBd.setNombre(curso.getNombre());
            cursoUpdate = servicio.save(cursoBd);
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause()
                    .getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("data", cursoUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
