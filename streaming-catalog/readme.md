# Streaming Catalog - Intern Challenge

Proyecto Java que procesa un catalogo de episodios a partir de un CSV con datos inconsistentes.
El objetivo es limpiar registros, descartar entradas invalidas, detectar duplicados y generar salidas listas para analisis.

## Que hace

1. Lee `input/episodes.csv`.
2. Normaliza campos (texto, numeros y fechas).
3. Valida registros minimos requeridos.
4. Deduplica episodios con una estrategia de llaves flexibles.
5. Genera:
	 - `output/episodes_clean.csv`
	 - `output/report.md`

## Reglas principales

- **Normalizacion**:
	- `trim` y colapso de espacios en texto.
	- Numeros negativos pasan a `0`.
	- Titulo vacio o nulo pasa a `Untitled Episode`.
	- Fechas invalidas quedan como vacias (`Unknown` en salida).

- **Validacion**:
	- Se descarta si `seriesName` esta vacio.
	- Se descarta si faltan al mismo tiempo: numero de episodio, titulo real y fecha.

- **Deduplicacion**:
	- K1: `series + season + episode`
	- K2: `series + episode + title` (cuando falta temporada)
	- K3: `series + season + title` (cuando falta numero de episodio)
	- Ante conflicto, se conserva el registro de mejor calidad (fecha valida, titulo real, numeros validos).

## Estructura

- `src/main/java/org/fabrizio/streaming/input/episodes.csv`: archivo fuente.
- `src/main/java/org/fabrizio/streaming/output/episodes_clean.csv`: catalogo limpio.
- `src/main/java/org/fabrizio/streaming/output/report.md`: reporte de calidad.
- `src/main/java/org/fabrizio/streaming/Main.java`: punto de entrada.

## Requisitos

- Java 25
- Maven

## Ejecucion

Desde la carpeta `streaming-catalog`:

```bash
mvn clean compile
```

Luego ejecuta `Main` desde un IDE (VS Code/IntelliJ) para generar los archivos de salida.
