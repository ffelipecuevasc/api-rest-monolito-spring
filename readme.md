# 🐳 api-rest-monolito-spring

> **Proyecto educativo** usado en el curso **"Docker para desarrolladores Java"**. Sirve como punto de partida para aprender a montar entornos de desarrollo locales con contenedores.

---

## 🎯 ¿Qué vas a aprender con este proyecto?

Este proyecto acompaña el curso de Docker y te permitirá entender de forma práctica:

- Por qué los entornos de desarrollo sin Docker generan problemas de configuración y conflictos
- Cómo ejecutar servicios de infraestructura (base de datos, mensajería, caché) dentro de contenedores
- Cómo orquestar múltiples contenedores al mismo tiempo con **Docker Compose**
- Cómo empaquetar una aplicación Java en una imagen Docker usando un **Dockerfile**
- Cómo simular un entorno cercano al de producción en tu máquina local

---

## 🧠 ¿Por qué Docker en el desarrollo?

Antes de Docker, montar un entorno local significaba:

```
❌ Instalar PostgreSQL, RabbitMQ, Redis y otras herramientas manualmente
❌ Gestionar versiones distintas entre desarrolladores del equipo
❌ Compartir credenciales de servicios reales de dev/qa con todos
❌ Riesgo de alterar datos o colas que otros compañeros también usan
❌ "En mi máquina funciona" — el problema clásico
```

Con Docker:

```
✅ Todos los servicios se levantan con un solo comando
✅ Todos los desarrolladores usan exactamente la misma versión
✅ El entorno local está completamente aislado
✅ Nadie toca datos de entornos compartidos
✅ Fácil de destruir y volver a crear desde cero
```

---

## 🏗️ Arquitectura del proyecto

```
                        ┌─────────────────────────────────────┐
                        │           api-producto               │
                        │        Java 21 + Spring Boot         │
                        │     CRUD de productos (REST API)     │
                        └──────────────┬──────────────────────┘
                                       │
              ┌────────────────────────┼──────────────────────────┐
              │                        │                          │
              ▼                        ▼                          ▼
     ┌────────────────┐    ┌──────────────────────┐   ┌─────────────────┐
     │   PostgreSQL   │    │      RabbitMQ         │   │     Redis       │
     │  Base de datos │    │  Cola de mensajería   │   │     Caché       │
     └────────────────┘    └──────────────────────┘   └─────────────────┘
              │                        │                          │
              └────────────────────────┼──────────────────────────┘
                                       │
              ┌────────────────────────┼──────────────────────────┐
              │                        │
              ▼                        ▼
     ┌────────────────┐    ┌──────────────────────┐
     │   LocalStack   │    │       Zipkin          │
     │  Simulador AWS │    │  Trazabilidad (logs)  │
     └────────────────┘    └──────────────────────┘
```

Todos estos servicios corren como **contenedores Docker** en tu máquina local. La aplicación Spring Boot se conecta a ellos igual que lo haría en producción.

---

## 🛠️ Stack tecnológico

### Aplicación

| Tecnología | Versión | ¿Por qué se usa? |
|---|---|---|
| Java | 21 | LTS actual — versión estable recomendada |
| Spring Boot | 3.x | Framework principal del backend |
| Spring Data JPA | — | Comunicación con PostgreSQL |
| Spring AMQP | — | Comunicación con RabbitMQ |
| Spring Data Redis | — | Comunicación con Redis |

### Infraestructura (Docker Compose)

| Servicio | Imagen Docker | ¿Qué hace en el proyecto? |
|---|---|---|
| PostgreSQL | `postgres:14.20-alpine3.23` | Guarda los productos en base de datos |
| RabbitMQ | `rabbitmq:3-management` | Cola de mensajes entre servicios |
| Redis | `redis` | Caché para mejorar el rendimiento |
| LocalStack | `localstack/localstack` | Simula servicios de AWS (S3, SQS, etc.) localmente |
| Zipkin | `openzipkin/zipkin` | Trazabilidad distribuida — visualiza el flujo de requests |

---

## 🧩 Conceptos clave explicados

### ¿Qué es un contenedor?

Un contenedor es una unidad de software que empaqueta el código y todas sus dependencias para que la aplicación se ejecute de forma rápida y confiable en cualquier entorno.

```
Máquina Virtual                    Contenedor
┌──────────────────────┐          ┌──────────────────────┐
│  App                 │          │  App                 │
│  Librerías propias   │          │  Librerías propias   │
│  Sistema Operativo   │          ├──────────────────────┤
│  completo (pesado)   │          │  Docker Engine       │
└──────────────────────┘          │  (comparte el kernel)│
                                  └──────────────────────┘
~GB de tamaño                     ~MB de tamaño ✅
```

> 💡 La clave: el contenedor **comparte el kernel del sistema operativo** anfitrión, por eso es mucho más ligero que una máquina virtual.

---

### ¿Qué es Docker Compose?

Docker Compose es una herramienta para definir y ejecutar múltiples contenedores al mismo tiempo usando un archivo de configuración declarativo (`docker-compose.yml`).

En lugar de ejecutar cada contenedor por separado:
```bash
# Sin Docker Compose (tedioso)
docker run postgres ...
docker run rabbitmq ...
docker run redis ...
docker run localstack ...
docker run zipkin ...
```

Con Docker Compose, un solo comando levanta todo:
```bash
# Con Docker Compose ✅
docker compose up
```

---

### ¿Qué es un Dockerfile?

Un **Dockerfile** es un archivo de instrucciones que le dice a Docker cómo construir la imagen de tu aplicación. Es como una receta paso a paso.

```dockerfile
FROM alpine/java:21-jdk          # Parte de una imagen con Java 21
COPY aplicacion.jar app.jar       # Copia el jar compilado dentro de la imagen
ENTRYPOINT ["java", "-jar", "app.jar"]  # Define cómo arrancar la aplicación
```

---

### ¿Qué son los perfiles de Spring?

Spring Boot permite tener configuraciones diferentes según el entorno. Este proyecto usa:

| Perfil | ¿Cuándo se usa? | ¿A qué infra apunta? |
|---|---|---|
| `local` | Desarrollo en tu máquina | Servicios locales en Docker |
| `infra_local` | Configuración de infra local | Puertos y credenciales de Docker local |
| `dev` | Dentro del contenedor Docker | Servicios del Docker Compose |
| `infra_dev` | Configuración de infra en Docker | Red interna de Docker Compose |

> 💡 **¿Por qué dos perfiles a la vez?** (`local,infra_local`) Spring permite combinarlos para separar la configuración de negocio (`local`) de la configuración de infraestructura (`infra_local`). Así puedes cambiar solo la infra sin tocar la lógica de negocio.

---

## ✅ Requisitos previos

Asegúrate de tener instalado:

- ☑️ [Java 21](https://adoptium.net/)
- ☑️ [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/)
- ☑️ [Maven 3.9+](https://maven.apache.org/)
- ☑️ [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- ☑️ [DBeaver Community](https://dbeaver.io/download/) — cliente gráfico para PostgreSQL
- ☑️ [Postman](https://www.postman.com/downloads/) — para probar los endpoints

---

## 🚀 Cómo ejecutar el proyecto

### Opción A — Modo desarrollo (app local + infra en Docker)

Esta es la forma recomendada para desarrollar, porque puedes hacer cambios en el código y relanzar la app rápidamente.

**Paso 1 — Levantar la infraestructura con Docker Compose:**

```bash
docker compose up
```

Esto levanta PostgreSQL, RabbitMQ, Redis, LocalStack y Zipkin como contenedores.

**Paso 2 — Compilar el proyecto:**

```bash
mvn clean install
```

**Paso 3 — Ejecutar la aplicación en IntelliJ:**

Abre `Application.java` → clic derecho → `Run 'Application'`

Configura el perfil activo en las opciones de ejecución:
```
spring.profiles.active=local,infra_local
```

---

### Opción B — Todo en Docker (app + infra en contenedores)

Esta opción simula más fielmente el entorno real de producción.

**Paso 1 — Compilar el proyecto sin ejecutar los tests:**

```bash
mvn clean install -DskipTests
```

**Paso 2 — Construir la imagen Docker de la aplicación:**

```bash
docker build -t api-producto .
```

**Paso 3 — Levantar todo con Docker Compose:**

```bash
docker compose up
```

La API queda disponible en `http://localhost:8080`

---

## 📡 Endpoints de la API

### Productos

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/api/v1/productos` | Crea un nuevo producto |
| `GET` | `/api/v1/productos` | Lista todos los productos |
| `GET` | `/api/v1/productos/{id}` | Obtiene un producto por ID |
| `PUT` | `/api/v1/productos/{id}` | Actualiza un producto |
| `DELETE` | `/api/v1/productos/{id}` | Elimina un producto |

**Ejemplo de creación de producto:**

```bash
curl --location 'http://localhost:8080/api/v1/productos' \
  --header 'Content-Type: application/json' \
  --data '{
    "nombre": "Producto 1",
    "categoria": "Categoria 1",
    "status": "AVAILABLE",
    "descripcion": "Descripción del producto 1",
    "valor": 1.99,
    "tags": ["tag1"]
  }'
```

---

## 🔍 Herramientas de monitoreo incluidas

### RabbitMQ Management
Interfaz web para visualizar colas, mensajes y consumidores.
```
URL:      http://localhost:9090
Usuario:  rabbitmq
Password: rabbitmq
```

### Zipkin
Visualiza el flujo completo de un request a través de los servicios.
```
URL: http://localhost:9411
```

### LocalStack
Simula servicios de AWS (S3, SQS, SNS, etc.) en tu máquina local.
```
URL: http://localhost:4566
```

---

## 📁 Estructura del proyecto

```
api-producto/
├── application/
│   └── src/main/java/
│       └── Application.java              ← Punto de entrada de Spring Boot
├── Dockerfile                            ← Instrucciones para construir la imagen Docker
├── docker-compose.yml                    ← Orquestación de todos los contenedores
└── src/
    └── main/
        └── resources/
            ├── application.properties            ← Configuración base
            ├── application-local.properties      ← Config para desarrollo local
            ├── application-infra_local.properties← Config de infra local (Docker)
            ├── application-dev.properties        ← Config para entorno dev
            └── application-infra_dev.properties  ← Config de infra en Docker Compose
```

---

## 🧪 Preguntas para reflexionar

Después de ejecutar el proyecto, intenta responder estas preguntas:

1. ¿Qué pasa con los datos de PostgreSQL si haces `docker compose down` y vuelves a levantar los contenedores? ¿Cómo lo solucionarías?
2. ¿Por qué usamos `alpine/java:21-jdk` como imagen base en el Dockerfile y no instalamos Java manualmente?
3. ¿Cuál es la diferencia entre el perfil `local` y el perfil `dev`? ¿Por qué necesitamos ambos?
4. ¿Por qué LocalStack es útil en el desarrollo? ¿Qué problema resuelve frente a usar AWS real?
5. ¿Qué ventaja tiene Zipkin en un proyecto con múltiples servicios?
6. Si agregas un nuevo desarrollador al equipo, ¿qué pasos necesita seguir para tener el entorno listo?

---

## 📚 Recursos recomendados

- [Documentación oficial de Docker](https://docs.docker.com/)
- [Docker Hub](https://hub.docker.com/) — repositorio de imágenes oficiales
- [Docker Compose reference](https://docs.docker.com/compose/)
- [Dockerfile reference](https://docs.docker.com/reference/dockerfile/)
- [LocalStack Cloud](https://app.localstack.cloud/) — consola web para verificar servicios simulados
- [Zipkin](https://zipkin.io/) — trazabilidad distribuida

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT** — puedes usarlo, modificarlo y compartirlo libremente.

---

<p align="center">
  Proyecto educativo del curso <strong>"Docker para desarrolladores Java"</strong> ☕🐳<br/>
  <strong>Java 21 · Spring Boot · Docker · Docker Compose · PostgreSQL · RabbitMQ · Redis · LocalStack · Zipkin</strong>
</p>
