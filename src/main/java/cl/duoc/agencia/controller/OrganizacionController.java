package cl.duoc.agencia.controller;

import cl.duoc.agencia.model.Organizacion;
import cl.duoc.agencia.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizaciones")
public class OrganizacionController {

    @Autowired
    private OrganizacionService organizacionService;

    // 1. GET: Listar todas
    @GetMapping
    public ResponseEntity<List<Organizacion>> listar() {
        return ResponseEntity.ok(organizacionService.findAll());
    }

    // 2. GET: Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Organizacion> buscarPorId(@PathVariable Long id) {
        Organizacion organizacion = organizacionService.buscarPorId(id);
        if (organizacion == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(organizacion);
    }

    // 3. POST: Registrar / Crear
    @PostMapping
    public ResponseEntity<Organizacion> registrar(@RequestBody Organizacion organizacion) {
        Organizacion nueva = organizacionService.registrar(organizacion);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // 4. PUT: Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Organizacion> actualizar(@PathVariable Long id, @RequestBody Organizacion organizacion) {
        Organizacion actualizada = organizacionService.actualizar(id, organizacion);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // 5. DELETE: Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        if (organizacionService.borrar(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}
