package cl.duoc.agencia.service;

import cl.duoc.agencia.model.Organizacion;
import cl.duoc.agencia.repository.OrganizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacionService {
    @Autowired
    private OrganizacionRepository organizacionRepository;

    // 1. Listar todas
    public List<Organizacion> findAll() {
        return organizacionRepository.findAll();
    }

    // 2. Buscar por ID
    public Organizacion buscarPorId(Long id) {
        return organizacionRepository.findById(id).orElse(null);
    }

    // 3. Registrar (Crear)
    public Organizacion registrar(Organizacion organizacion) {
        organizacion.setId(null); // el id lo genera la base de datos
        return organizacionRepository.save(organizacion);
    }

    // 4. Borrar
    public boolean borrar(Long id) {
        if (organizacionRepository.existsById(id)) {
            organizacionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 5. Actualizar
    public Organizacion actualizar(Long id, Organizacion detalles) {
        return organizacionRepository.findById(id).map(organizacion -> {
            organizacion.setNombre(detalles.getNombre());
            organizacion.setTipo(detalles.getTipo());
            organizacion.setRubro(detalles.getRubro());
            organizacion.setComuna(detalles.getComuna());
            organizacion.setSitioWeb(detalles.getSitioWeb());
            organizacion.setPublicoAsociado(detalles.getPublicoAsociado());
            organizacion.setObservaciones(detalles.getObservaciones());
            return organizacionRepository.save(organizacion);
        }).orElse(null);
    }
}
