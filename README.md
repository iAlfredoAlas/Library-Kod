# Proyecto Biblioteca

Este proyecto es una aplicación para gestionar una biblioteca, donde se pueden administrar autores, libros, editoriales, géneros, 
reservas, empleados y usuarios. La aplicación sigue los principios de diseño SOLID para mantener un código limpio y modular.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Web
- ModelMapper
- Lombok
- MySQL
- JasperReports

## Base de Datos

La base de datos utilizada en este proyecto es MySQL. A continuación, se detallan los pasos para recrear la base de datos de forma 
local:

1. Asegúrate de tener MySQL instalado en tu máquina.

2. Crea una nueva base de datos con el nombre "library" en MySQL.

3. Ejecuta los queries iniciales que se encuentran en la carpeta `resources` ubicado en la raíz del proyecto. Estos queries crearán
4. las tablas necesarias y agregarán algunos datos iniciales para probar la aplicación.

## Endpoints API

Para facilitar el consumo de la API, hemos proporcionado una colección de Postman que contiene todos los endpoints necesarios.
Puedes encontrar el archivo `Library-API_KOD.postman_collection.json` en la carpeta `resources` ubicada en raíz del proyecto.
Importa esta colección en Postman y tendrás acceso a los diferentes endpoints para realizar pruebas y consumir la API.


## Arquitectura del Proyecto

El proyecto sigue una arquitectura de capas, dividida en:

- **Modelos (Entities):** Contiene las entidades de datos que representan las tablas de la base de datos.

- **DTO (Data Transfer Objects):** Proporciona objetos de transferencia de datos para intercambiar información entre las capas.

- **Repositorios (Repositories):** Se encarga de interactuar con la base de datos a través de Spring Data JPA.

- **Servicios (Services):** Implementa la lógica de negocio y actúa como interfaz entre los controladores y los repositorios.

- **Controladores (Controllers):** Maneja las solicitudes HTTP y se comunica con los servicios para obtener respuestas.

- **Utility:** Contiene la clase `ResponseFactory`, que proporciona métodos para generar respuestas HTTP estandarizadas.

- **Main Class:** Clase principal de la aplicación.

## Principios SOLID

El proyecto cumple con los siguientes principios SOLID:

- **Principio de Responsabilidad Única (SRP):** Todos los archivos de modelos, DTO, repositorios, servicios y controladores cumplen
- con este principio, ya que cada uno tiene una sola responsabilidad específica.

- **Principio Abierto-Cerrado (OCP):** Los controladores y la clase `ResponseFactory` cumplen con este principio, ya que pueden
extenderse para agregar nuevas funcionalidades sin modificar el código existente.

- **Principio de Sustitución de Liskov (LSP):** Los controladores cumplen con este principio, ya que implementan la interfaz
`ICrudGenericController` y pueden ser sustituidos entre sí sin afectar el comportamiento del programa.

- **Principio de Segregación de Interfaces (ISP):** El proyecto utiliza dos interfaces `ICrudGenericController` e `ICrudService`,
que podrían considerarse interfaces "grandes" que contienen métodos que no todos los controladores o servicios utilizan.
Se podría mejorar dividiendo las interfaces en interfaces más específicas y cohesivas.

- **Principio de Inversión de Dependencias (DIP):** Los controladores dependen de los servicios a través de la inyección de dependencias,
lo que cumple con este principio.

## Ejecución del Proyecto

1. Clona el repositorio a tu máquina local.
2. Abre el proyecto en tu IDE (por ejemplo, IntelliJ o Eclipse).
3. Asegúrate de tener configurada una base de datos compatible y actualiza las credenciales en el archivo `application.properties`.
4. Ejecuta la clase `LibraryApplication` para iniciar la aplicación.
5. La aplicación estará disponible en `http://localhost:8083`.
