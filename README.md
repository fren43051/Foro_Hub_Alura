Foro Hub
Descripción
Foro Hub es una aplicación web para crear y responder tópicos (preguntas o debates) relacionados con cursos. Los usuarios pueden registrarse, iniciar sesión, crear nuevos tópicos, responder a tópicos existentes y marcar respuestas como soluciones.

Funcionalidades Principales
Autenticación: Los usuarios deben registrarse e iniciar sesión para interactuar con los tópicos.
Creación de Tópicos: Los usuarios pueden crear nuevos tópicos, proporcionando un título, un mensaje y seleccionando el curso al que pertenece el tópico.
Listado de Tópicos: Se pueden ver todos los tópicos abiertos, ordenados por fecha de creación.
Actualización de Tópicos: Los autores de los tópicos pueden editar el título y el mensaje de sus tópicos.
Eliminación de Tópicos (Lógica): Los autores de los tópicos pueden "eliminar" sus tópicos. En realidad, se marcan como cerrados.
Respuestas a Tópicos: Los usuarios pueden responder a los tópicos existentes.
Marcar Solución: El autor de un tópico puede marcar una respuesta como la solución al tópico.
Listado de Tópicos Cerrados: Se pueden listar los tópicos que han sido cerrados.
Tecnologías Utilizadas
Java 17: Lenguaje de programación principal.
Spring Boot: Framework para desarrollo de aplicaciones web.
Spring Security: Para autenticación y autorización de usuarios.
JWT (JSON Web Tokens): Para gestionar la autenticación de usuarios de forma segura.
Jakarta Persistence API (JPA): Para la persistencia de datos en una base de datos relacional.
Hibernate: Implementación de JPA.
MySQL: Base de datos utilizada.
Configuración
Base de Datos:
Asegúrate de tener una base de datos MySQL en funcionamiento.
Configura las credenciales de acceso a la base de datos en el archivo application.properties.
Uso
Registro:

Envía una solicitud POST a /usuario/registrar con los datos del usuario (nombre, correo electrónico, contraseña y perfil) en formato JSON.
Inicio de Sesión:

Envía una solicitud POST a /login con el nombre de usuario y contraseña en formato JSON.
Si las credenciales son válidas, recibirás un token JWT en la respuesta.
Incluye el token JWT en el encabezado Authorization de las solicitudes posteriores (por ejemplo, Bearer <token>).
Creación de Tópicos:

Envía una solicitud POST a /topico con los datos del tópico (idUsuario, mensaje, título, nombreCurso) en formato JSON.
Listado de Tópicos:

Envía una solicitud GET a /topico para ver todos los tópicos abiertos. Puedes usar parámetros de paginación y ordenamiento.
Actualización de Tópicos:

Envía una solicitud PUT a /topico/{id} con los nuevos datos del tópico en formato JSON.
Eliminación de Tópicos (Lógica):

Envía una solicitud DELETE a /topico/{id} para marcar un tópico como cerrado.
Listado de Tópicos Cerrados:

Envía una solicitud GET a /topico/cerrados para ver todos los tópicos cerrados. Puedes usar parámetros de paginación y ordenamiento.
Datos de inicio de sesión de prueba
{
  "nombre": "nelson.reyes",
  "contrasena": "280480"
}
