package cl.duoc.agencia.controller;

import cl.duoc.agencia.model.Organizacion;
import cl.duoc.agencia.service.OrganizacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizacionControllerTest {

    @Mock
    private OrganizacionService service;

    @InjectMocks
    private OrganizacionController controller;

    private Organizacion crear(Long id) {
        return new Organizacion(id, "Org", "EMPRESA", "Retail", "Santiago", "", "", "");
    }

    @Test
    void listar_devuelve200ConLaLista() {
        when(service.findAll()).thenReturn(List.of(crear(1L), crear(2L)));

        ResponseEntity<List<Organizacion>> respuesta = controller.listar();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(2, respuesta.getBody().size());
    }

    @Test
    void buscarPorId_existente_devuelve200() {
        Organizacion org = crear(1L);
        when(service.buscarPorId(1L)).thenReturn(org);

        ResponseEntity<Organizacion> respuesta = controller.buscarPorId(1L);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(org, respuesta.getBody());
    }

    @Test
    void buscarPorId_inexistente_devuelve404() {
        when(service.buscarPorId(99L)).thenReturn(null);

        assertEquals(HttpStatus.NOT_FOUND, controller.buscarPorId(99L).getStatusCode());
    }

    @Test
    void registrar_devuelve201ConLaOrganizacionCreada() {
        Organizacion entrada = crear(null);
        Organizacion creada = crear(7L);
        when(service.registrar(entrada)).thenReturn(creada);

        ResponseEntity<Organizacion> respuesta = controller.registrar(entrada);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertEquals(7L, respuesta.getBody().getId());
    }

    @Test
    void actualizar_existente_devuelve200() {
        Organizacion entrada = crear(null);
        Organizacion actualizada = crear(1L);
        when(service.actualizar(1L, entrada)).thenReturn(actualizada);

        ResponseEntity<Organizacion> respuesta = controller.actualizar(1L, entrada);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertSame(actualizada, respuesta.getBody());
    }

    @Test
    void actualizar_inexistente_devuelve404() {
        Organizacion entrada = crear(null);
        when(service.actualizar(99L, entrada)).thenReturn(null);

        assertEquals(HttpStatus.NOT_FOUND, controller.actualizar(99L, entrada).getStatusCode());
    }

    @Test
    void borrar_existente_devuelve204() {
        when(service.borrar(1L)).thenReturn(true);

        assertEquals(HttpStatus.NO_CONTENT, controller.borrar(1L).getStatusCode());
    }

    @Test
    void borrar_inexistente_devuelve404() {
        when(service.borrar(99L)).thenReturn(false);

        assertEquals(HttpStatus.NOT_FOUND, controller.borrar(99L).getStatusCode());
    }
}
