package cl.duoc.agencia.config;

import cl.duoc.agencia.model.Organizacion;
import cl.duoc.agencia.repository.OrganizacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataInitializerTest {

    @Mock
    private OrganizacionRepository repository;

    @Test
    void conBaseVacia_cargaTresOrganizacionesDeEjemplo() throws Exception {
        when(repository.count()).thenReturn(0L);
        CommandLineRunner runner = new DataInitializer().cargarDatosDemo(repository);

        runner.run();

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<Organizacion>> captor = ArgumentCaptor.forClass(List.class);
        verify(repository).saveAll(captor.capture());
        assertEquals(3, captor.getValue().size());
    }

    @Test
    void conDatosExistentes_noInsertaNada() throws Exception {
        when(repository.count()).thenReturn(3L);
        CommandLineRunner runner = new DataInitializer().cargarDatosDemo(repository);

        runner.run();

        verify(repository, never()).saveAll(anyList());
    }
}
