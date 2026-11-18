package cl.duoc.agencia.service;

import cl.duoc.agencia.model.Organizacion;
import cl.duoc.agencia.repository.OrganizacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizacionServiceTest {

    @Mock
    private OrganizacionRepository repository;

    @InjectMocks
    private OrganizacionService service;

    private Organizacion crear(Long id, String nombre) {
        return new Organizacion(id, nombre, "EMPRESA", "Retail", "Santiago",
                "https://www.ejemplo-demo.cl", "Trabajadores", "obs");
    }

    @Test
    void findAll_devuelveTodasLasOrganizaciones() {
        when(repository.findAll()).thenReturn(List.of(crear(1L, "A"), crear(2L, "B")));

        List<Organizacion> resultado = service.findAll();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void buscarPorId_existente_devuelveLaOrganizacion() {
        Organizacion org = crear(1L, "A");
        when(repository.findById(1L)).thenReturn(Optional.of(org));

        assertSame(org, service.buscarPorId(1L));
    }

    @Test
    void buscarPorId_inexistente_devuelveNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertNull(service.buscarPorId(99L));
    }

    @Test
    void registrar_guardaConIdNuloParaQueLoGenereLaBaseDeDatos() {
        Organizacion entrada = crear(55L, "Nueva");
        when(repository.save(any(Organizacion.class))).thenAnswer(inv -> inv.getArgument(0));

        Organizacion guardada = service.registrar(entrada);

        assertNull(guardada.getId());
        assertEquals("Nueva", guardada.getNombre());
        verify(repository).save(entrada);
    }

    @Test
    void borrar_existente_eliminaYDevuelveTrue() {
        when(repository.existsById(1L)).thenReturn(true);

        assertTrue(service.borrar(1L));
        verify(repository).deleteById(1L);
    }

    @Test
    void borrar_inexistente_devuelveFalseYNoElimina() {
        when(repository.existsById(99L)).thenReturn(false);

        assertFalse(service.borrar(99L));
        verify(repository, never()).deleteById(any());
    }

    @Test
    void actualizar_existente_copiaTodosLosCampos() {
        Organizacion existente = crear(1L, "Vieja");
        Organizacion detalles = new Organizacion(null, "Nueva", "MUNICIPALIDAD", "Gobierno",
                "Puente Alto", "https://otro.cl", "Vecinos", "cambio");
        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(Organizacion.class))).thenAnswer(inv -> inv.getArgument(0));

        Organizacion resultado = service.actualizar(1L, detalles);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Nueva", resultado.getNombre());
        assertEquals("MUNICIPALIDAD", resultado.getTipo());
        assertEquals("Gobierno", resultado.getRubro());
        assertEquals("Puente Alto", resultado.getComuna());
        assertEquals("https://otro.cl", resultado.getSitioWeb());
        assertEquals("Vecinos", resultado.getPublicoAsociado());
        assertEquals("cambio", resultado.getObservaciones());
    }

    @Test
    void actualizar_inexistente_devuelveNullYNoGuarda() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertNull(service.actualizar(99L, crear(null, "X")));
        verify(repository, never()).save(any());
    }
}
