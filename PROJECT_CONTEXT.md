# tuSQL - Contexto y Estado del Proyecto

Este archivo sirve como memoria técnica para el desarrollo de la aplicación **tuSQL** (anteriormente Sunflower).

## 🚀 Estado Actual
La aplicación ha sido migrada de tuSQL a **tuSQL**, enfocándose en la visibilidad y gestión de datos SQLite/Room.

### Cambios Realizados:
- **Rebranding Completo:** Cambio de nombre en recursos (`strings.xml`), configuración de Gradle (`settings.gradle.kts`) y documentación (`README.md`).
- **Inspector de Base de Datos:** Se implementó una pantalla interna (`DatabaseInspectorScreen`) para visualizar en tiempo real las tablas `plants` y `garden_plantings`.
- **Iconografía:** Se añadió un icono de base de datos (`ic_database.xml`) personalizado en la barra superior.
- **Sincronización Git:** El proyecto está vinculado y actualizado en `https://github.com/cguevara962/tuSQL.git`.

## 🛠️ Stack Tecnológico (Configuración Crítica)
Para mantener la estabilidad del proyecto, se deben respetar estas versiones (ajustadas para compatibilidad con Kotlin 2.2.x):
- **Kotlin:** 2.2.0 / 2.2.10
- **Hilt:** 2.57.1 (Versión específica para soportar metadatos de Kotlin 2.2.0 en AGP 8.x/9.x)
- **Room:** 2.8.4 (Necesario para corregir errores de firmas JVM en KSP2)
- **Gradle Wrapper:** 9.5.0
- **KSP:** Configurado para funcionar con el motor KSP1/KSP2 según la estabilidad del build.

## 📌 Pendientes y Próximos Pasos
- [ ] **Edición en Inspector:** Permitir editar o eliminar registros directamente desde la pantalla de inspección de tablas.
- [ ] **Limpieza de Paquetes:** Opcionalmente, renombrar el paquete técnico `com.tusql.app` a `com.tuSQL.app`.
- [ ] **Nuevas Tablas:** Si se añaden más entidades a Room, registrarlas en el `DatabaseViewModel` e incluirlas en el inspector.
- [ ] **UI/UX:** Mejorar el diseño visual de las filas en el inspector de tablas para que sea más legible (filtros, búsqueda).
- [ ] **Creación Dinámica de Tablas:** Implementar un motor SQL puro (SupportSQLiteDatabase) para permitir al usuario definir y crear sus propias tablas en tiempo de ejecución.
- [ ] **Exposición de Datos como Servicio:** Desarrollar una capa de servicio empresarial (estilo ContentProvider o API local con Ktor) para permitir el acceso seguro a los datos desde aplicaciones externas o dispositivos en la misma red.

---
*Documento generado el 16 de Agosto de 2026 para asegurar la continuidad del desarrollo.*
