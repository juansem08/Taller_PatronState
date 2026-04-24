# Digital Twin: Reality Coherence 🌌

Este proyecto es una implementación profesional del **Patrón de Diseño State** para gestionar la coherencia de un sensor en un entorno de Gemelo Digital. El sistema transita secuencialmente por cuatro fases críticas de la física simulada.

---

## 🚀 Arquitectura del Proyecto

### 1. Backend (Java 21 + Spring Boot)
Ubicado en la carpeta `/backend`. Implementa la lógica de estados en el paquete `com.reality.core`.
- **Fases del Ciclo de Vida**: `Newtonian` ➔ `Anomalous` ➔ `Speculative` ➔ `Quantum`.
- **Patrón State**: Cada estado gestiona su propia transición a la siguiente fase.
- **Simulación**: Al iniciar, el sistema ejecuta automáticamente una simulación completa en la consola.
- **API REST**: Expone un endpoint en `http://localhost:8080/api/coherence` para la comunicación con el frontend.

### 2. Frontend (React + Tailwind)
Ubicado en la carpeta `/frontend`. 
- **Simplicidad**: Se ha simplificado a un único archivo `index.html` que carga React y Tailwind vía CDN para evitar el uso de `node_modules`.
- **Interfaz "Command Center"**: Estética de alta tecnología en modo oscuro con indicadores de fase animados.

---

## 🛠️ Instrucciones de Ejecución

### Ejecutar el Servidor (Backend)
1. Abre la carpeta `backend` en tu IDE (VS Code, IntelliJ, etc.).
2. Abre el archivo `src/main/java/com/reality/core/RealityApplication.java`.
3. Haz clic en el botón **"Run"** de tu editor. 
   *(Nota: Si no tienes Maven instalado, el editor descargará lo necesario automáticamente).*

### Ejecutar la Interfaz (Frontend)
1. Ve a la carpeta `frontend`.
2. Simplemente haz **doble clic** en el archivo `index.html` para abrirlo en tu navegador.
3. Interactúa con el botón **"Avanzar Fase"** para ver los cambios en tiempo real sincronizados con el servidor Java.

---

## 📝 Notas de Desarrollo
- El código fuente (nombres de variables, clases y métodos) sigue convenciones profesionales en **Inglés**.
- La interfaz de usuario ha sido localizada íntegramente al **Español**.
- En la fase **Anómala**, el sistema emite una alerta crítica visible en los logs del servidor.
