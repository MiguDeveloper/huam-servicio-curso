package pe.tuna.microserviciocurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.tuna.microserviciocurso.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c JOIN FETCH c.alumnos a WHERE a.id = ?1")
    public Curso findCursoByAlumnoId(Long id);
}
