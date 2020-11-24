package pe.tuna.microserviciocurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.tuna.microserviciocurso.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
