package pe.tuna.microserviciocurso.entity;

import pe.tuna.commonsalumnos.models.Alumno;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public Curso(){
        alumnos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void addAlumno(Alumno alumno){
        alumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno){
        alumnos.remove(alumno);
    }

}
