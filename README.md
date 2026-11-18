# agencia-api

API REST (Spring Boot 4 + Java 21 + Maven + MySQL) para la app Android **Agencia Oportunidades**. Basada en `miapi`.

## Cómo levantarla
1. Abre **XAMPP** y presiona *Start* en **MySQL** (puerto 3306, usuario `root` sin contraseña).
2. Abre esta carpeta en **IntelliJ** (File > Open > `pom.xml`) y ejecuta `AgenciaApiApplication`
   (o `./mvnw spring-boot:run`).
3. La base `bd_agencia` y la tabla `organizaciones` se crean solas, con 3 organizaciones de ejemplo.
4. Prueba en el navegador: <http://localhost:8080/organizaciones>

Desde el emulador de Android Studio la API se ve en `http://10.0.2.2:8080/` (ya configurado en la app).

## Endpoints `/organizaciones`
| Método | Ruta | Respuesta |
|---|---|---|
| GET | `/organizaciones` | 200 |
| GET | `/organizaciones/{id}` | 200 / 404 |
| POST | `/organizaciones` | 201 |
| PUT | `/organizaciones/{id}` | 200 / 404 |
| DELETE | `/organizaciones/{id}` | 204 / 404 |

```json
{
  "nombre": "Retail Andino S.A.",
  "tipo": "EMPRESA",
  "rubro": "Retail",
  "comuna": "Santiago",
  "sitioWeb": "https://www.ejemplo-demo.cl",
  "publicoAsociado": "Trabajadores y familias",
  "observaciones": ""
}
```
`tipo` usa los valores del enum `TipoOrganizacion` de la app (EMPRESA, MUNICIPALIDAD, INSTITUCION_PUBLICA, INSTITUCION_PRIVADA, ORGANIZADOR_EVENTOS, CENTRO_COMERCIAL, ASOCIACION, OTRA).

## Pruebas y cobertura
```
./mvnw verify
```
Ejecuta las pruebas unitarias (JUnit 5 + Mockito, no necesitan MySQL) y falla si la cobertura de líneas es menor a 80 %.
Reporte: `target/site/jacoco/index.html`.
