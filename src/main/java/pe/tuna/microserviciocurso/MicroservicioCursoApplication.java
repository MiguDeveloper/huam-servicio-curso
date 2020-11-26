package pe.tuna.microserviciocurso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"pe.tuna.commonsalumnos.models", "pe.tuna.microserviciocurso.entity"})
public class MicroservicioCursoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioCursoApplication.class, args);
    }

}
