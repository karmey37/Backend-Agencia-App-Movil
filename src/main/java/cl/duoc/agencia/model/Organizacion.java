package cl.duoc.agencia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organizaciones")
public class Organizacion {
    @Column(name = "id_organizacion")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    // Mismos valores del enum TipoOrganizacion de la app (EMPRESA, MUNICIPALIDAD, ...)
    @Column(nullable = false, length = 30)
    private String tipo;

    @Column(nullable = false, length = 60)
    private String rubro;

    @Column(nullable = false, length = 60)
    private String comuna;

    @Column(name = "sitio_web", length = 150)
    private String sitioWeb;

    @Column(name = "publico_asociado", length = 150)
    private String publicoAsociado;

    @Column(length = 500)
    private String observaciones;
}
