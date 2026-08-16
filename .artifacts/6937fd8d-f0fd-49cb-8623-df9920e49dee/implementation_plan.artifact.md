# Plan de Refactorización Completa: De Sunflower a tuSQL

Este plan detalla el proceso para renombrar todas las instancias de "Sunflower" a "tuSQL" en el proyecto, incluyendo el paquete técnico (`package name`), la estructura de directorios y toda la documentación.

## User Review Required

> [!IMPORTANT]
> Este es un cambio destructivo y masivo que afectará a casi todos los archivos del proyecto. Se cambiará el identificador de la aplicación (`applicationId`), lo que significa que en el dispositivo se tratará como una aplicación nueva y no como una actualización.

## Proposed Changes

### [Core Refactoring]

#### [MODIFY] [build.gradle.kts](file:///C:/Users/USUARUIO/StudioProjects/sunflower/app/build.gradle.kts)
- Cambiar `applicationId` y `namespace` a `com.tusql.app`.

#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/USUARUIO/StudioProjects/sunflower/app/src/main/AndroidManifest.xml)
- Actualizar referencias de clases al nuevo paquete.

#### [MOVE & MODIFY] Código Fuente Kotlin
- Mover archivos de `com/google/samples/apps/sunflower` a `com/tusql/app`.
- Actualizar la declaración `package` en cada archivo.
- Actualizar todos los `import` en el proyecto.

### [Documentation & Resources]

#### [MODIFY] [README.md](file:///C:/Users/USUARUIO/StudioProjects/sunflower/README.md)
- Reemplazar todas las menciones de "Sunflower" por "tuSQL".

#### [MODIFY] [PROJECT_CONTEXT.md](file:///C:/Users/USUARUIO/StudioProjects/sunflower/PROJECT_CONTEXT.md)
- Actualizar el estado y los pendientes.

#### [MODIFY] Recursos XML y JSON
- Actualizar `strings.xml`, `plants.json` y otros archivos de recursos.

## Verification Plan

### Automated Tests
- Ejecutar `./gradlew assembleDebug` para verificar que el proyecto compila.
- Ejecutar una prueba unitaria básica para confirmar que el mapeo de paquetes es correcto.

### Manual Verification
- Desplegar la aplicación en el emulador y verificar que el nombre y el funcionamiento sean correctos.
