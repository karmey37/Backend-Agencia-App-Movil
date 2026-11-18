package cl.duoc.agencia.config;

import cl.duoc.agencia.model.Organizacion;
import cl.duoc.agencia.repository.OrganizacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatosDemo(OrganizacionRepository organizacionRepository) {
        return args -> {
            if (organizacionRepository.count() == 0) {
                Organizacion org1 = new Organizacion(null, "Retail Andino S.A.", "EMPRESA", "Retail",
                        "Santiago", "https://www.ejemplo-demo.cl", "Trabajadores y familias", "");
                Organizacion org2 = new Organizacion(null, "Municipalidad de Puente Alto", "MUNICIPALIDAD", "Gobierno local",
                        "Puente Alto", "https://www.ejemplo-demo.cl", "Vecinos de la comuna", "");
                Organizacion org3 = new Organizacion(null, "Centro Comercial Plaza Sur", "CENTRO_COMERCIAL", "Comercio",
                        "San Bernardo", "https://www.ejemplo-demo.cl", "Visitantes y locatarios", "");

                organizacionRepository.saveAll(List.of(org1, org2, org3));
            }
        };
    }
}
