# Sistema de Cálculo de Tallas para Modista

## Resumen Ejecutivo

### Descripción
Sistema de escritorio desarrollado en Java para automatizar el cálculo de tallas en un negocio de confección de vestidos. La aplicación permite ingresar medidas corporales (busto, cintura y cadera) y determina automáticamente la talla adecuada, desde XXS hasta XXL.

### Problema Identificado
En el negocio de confección de vestidos, el proceso manual de cálculo de tallas presenta varios desafíos:
- Cálculos inexactos que resultan en ajustes posteriores
- Tiempo excesivo en la determinación de tallas
- Falta de registro histórico de medidas
- Inconsistencia en los criterios de medición
- Retrasos en entregas por modificaciones adicionales

### Solución
Se desarrolló una aplicación de escritorio que:
- Automatiza el cálculo de tallas basado en medidas específicas
- Implementa un algoritmo ponderado para mayor precisión
- Almacena histórico de medidas en base de datos local
- Proporciona interfaz intuitiva para ingreso de datos
- Valida rangos de medidas para evitar errores

### Arquitectura
El sistema está construido siguiendo una arquitectura en capas:
- **GUI**: Interfaz gráfica desarrollada con Java Swing
- **Modelo**: Clases de negocio para manejo de datos
- **Base de Datos**: SQLite para almacenamiento local
- **Utilidades**: Manejo de excepciones y validaciones

## Tabla de Contenidos
1. [Requerimientos](#requerimientos)
2. [Instalación](#instalación)
3. [Configuración](#configuración)
4. [Uso](#uso)
5. [Contribución](#contribución)
6. [Roadmap](#roadmap)

## Requerimientos

### Software Base
- Java SE Development Kit (JDK) 11 o superior
- Gradle 7.0 o superior (incluido en el wrapper)
- SQLite 3.x
- Sistema operativo: Windows, macOS, o Linux

### Paquetes y Dependencias
```gradle
dependencies {
    // Base de datos
    implementation 'org.xerial:sqlite-jdbc:3.43.0.0'
    
    // Logging
    implementation 'org.slf4j:slf4j-api:2.0.9'
    implementation 'ch.qos.logback:logback-classic:1.4.11'
    
    // GUI mejorada
    implementation 'org.swinglabs:swingx:1.6.5-1'
}
```

### Requisitos de Sistema
- Memoria RAM: 256MB mínimo
- Espacio en disco: 100MB mínimo
- Resolución de pantalla: 800x600 mínimo

## Instalación

### Ambiente de Desarrollo

1. Clonar el repositorio:
```bash
git clone https://github.com/alexcastroe20/productividad_basada_en_herramientas_tecnologicas.git
cd productividad_basada_en_herramientas_tecnologicas
```

2. Compilar el proyecto:
```bash
./gradlew build
```

3. Ejecutar la aplicación:
```bash
./gradlew run
```

### Ejecución de Pruebas

1. Ejecutar todas las pruebas:
```bash
./gradlew test
```

2. Ver reporte de cobertura (después de ejecutar las pruebas):
```bash
./gradlew jacocoTestReport
```
Los reportes se generan en: `build/reports/jacoco/test/html/index.html`

### Implementación en Producción

1. Generar el archivo JAR:
```bash
./gradlew jar
```

2. Ejecutar la aplicación:
```bash
java -jar build/libs/modista-app-1.0-SNAPSHOT.jar
```

## Configuración

### Archivos de Configuración

- `config/checkstyle/checkstyle.xml`: Reglas de estilo de código
- `config/spotbugs/exclude.xml`: Configuración de análisis de código
- `src/main/resources/logback.xml`: Configuración de logging

### Configuración de Base de Datos
La base de datos SQLite se crea automáticamente en:
```
modista.db
```

## Uso

### Manual de Usuario Final

1. **Inicio de la Aplicación**
   - Ejecutar el archivo JAR
   - La ventana principal mostrará los campos de entrada

2. **Ingreso de Medidas**
   - Nombre de la cliente
   - Medida de busto (70-150 cm)
   - Medida de cintura (50-130 cm)
   - Medida de cadera (80-160 cm)

3. **Cálculo de Talla**
   - Hacer clic en "Calcular Talla"
   - La talla se mostrará en pantalla
   - Opcional: Guardar los datos

### Manual de Administrador

1. **Gestión de Base de Datos**
   - Ubicación: `modista.db`
   - Herramienta recomendada: DB Browser for SQLite

2. **Logs del Sistema**
   - Ubicación: `logs/modista.log`
   - Nivel de log configurable en `logback.xml`

## Contribución

1. Fork del repositorio
2. Crear branch para la feature
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. Commit de cambios
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. Push al branch
   ```bash
   git push origin feature/AmazingFeature
   ```
5. Abrir Pull Request

### Guías de Contribución
- Seguir estilo de código (checkstyle)
- Incluir pruebas unitarias
- Actualizar documentación
- Seguir convenciones de commits

## Roadmap

### Próximas Características
1. **Corto Plazo (Q4 2025)**
   - Exportación de datos a Excel
   - Búsqueda avanzada de clientes
   - Impresión de fichas de medidas

2. **Mediano Plazo (Q1 2026)**
   - Integración con impresoras de etiquetas
   - Gestión de inventario de telas
   - Calendario de pruebas y entregas

3. **Largo Plazo (2026+)**
   - Versión móvil de la aplicación
   - Sincronización en la nube
   - Sistema de facturación integrado

## Licencia
Distribuido bajo la Licencia MIT. Ver `LICENSE` para más información.

## Contacto
Noé Alejandro Castro Encinia
AL02964080
Universidad TecMilenio

Link del Proyecto: https://github.com/alexcastroe20/productividad_basada_en_herramientas_tecnologicas.git
