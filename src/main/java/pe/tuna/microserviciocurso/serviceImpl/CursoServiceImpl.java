package pe.tuna.microserviciocurso.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.tuna.commonsmicroservicios.services.CommonServiceImpl;
import pe.tuna.microserviciocurso.entity.Curso;
import pe.tuna.microserviciocurso.repository.CursoRepository;
import pe.tuna.microserviciocurso.service.ICursoService;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository>
        implements ICursoService {
    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }
}
