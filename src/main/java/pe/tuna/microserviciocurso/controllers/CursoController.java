package pe.tuna.microserviciocurso.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.tuna.commonsalumnos.models.Alumno;
import pe.tuna.commonsmicroservicios.controllers.CommonController;
import pe.tuna.microserviciocurso.entity.Curso;
import pe.tuna.microserviciocurso.service.ICursoService;

import java.util.HashMap;
import java.util.List;
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

    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Curso cursoDb;
        Curso cursoUpdate;
        try {
            cursoDb = servicio.findById(id);
            alumnos.forEach(alumno -> cursoDb.addAlumno(alumno));
            cursoUpdate = servicio.save(cursoDb);
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause()
                    .getMessage()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("data", cursoUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Curso cursoDb;
        Curso cursoUpdate;
        try {
            cursoDb = servicio.findById(id);
            cursoDb.removeAlumno(alumno);
            cursoUpdate = servicio.save(cursoDb);
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause()
                    .getMessage()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("data", cursoUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarPorAlumno(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Curso curso;
        try {
            curso = servicio.findCursoByAlumnoId(id);
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause()
                    .getMessage()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("data", curso);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
