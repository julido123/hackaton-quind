# Read ME
Para utilizar la aplicación de gestión de torneos, se deben seguir los siguientes pasos:

Crear un Torneo: Se debe crear un Torneo. Enviar una solicitud POST a '/api/torneos' con el nombre, ubicación, deporte y descripción del torneo.Ejemplo de solicitud para crear un torneo:

POST /api/torneos
{
  "nombre": "Campeonato Nacional",
  "ubicacion": "Estadio Nacional",
  "deporte": "Fútbol",
  "descripcion": "Torneo de fútbol más importante del país"
}

Agregar Equipos: Una vez que tengamos un torneo, se deben añadir equipos. Utiliza una solicitud POST con request params a '/api/equipos' e incluir los detalles del equipo y el ID del torneo al que pertenecen.Ejemplo de solicitud para añadir un equipo:


POST /api/equipos?nombre=Los Halcones&descripcion=Equipo de alta competencia&numeroJugadores=11&idTorneo=1


Consultar Equipos o Torneos: Se pueden ver todos los equipos o torneos registrados enviando una solicitud GET a '/api/equipos' o '/api/torneos', respectivamente. Para ver un equipo o torneo específico, se puede usar su ID en la URL como /api/equipos/{id} o /api/torneos/{id}.

Crear Fixture: Con los equipos agregados, se crea un fixture enviando una solicitud POST a /api/fixtures. Se necesita proporcionar el ID del torneo en el cuerpo de la solicitud.Ejemplo de solicitud para crear un fixture:

POST /api/fixtures
{
  "id": 1
}

Eliminar Entidades: Si se necesita eliminar un torneo, equipo o fixture, se envía una solicitud DELETE a la URL correspondiente con el ID de la entidad. Por ejemplo, /api/torneos/{id}, /api/equipos/{id}, o /api/fixtures/{id}.
Consultar Fixtures: Puedes obtener una lista de todos los fixtures y sus detalles haciendo una solicitud GET a /api/fixtures. Si necesitas detalles de un fixture específico, sería con un ID específico, pero parece que no tienes un endpoint definido para eso.
Manejo de Errores: Si alguna operación falla, la API devolverá un código de estado HTTP apropiado. Por ejemplo, 400 Bad Request si falta información o 404 Not Found si se intenta acceder a una entidad que no existe.

Ejemplo cuando se crea un fixture
![image](https://github.com/julido123/hackaton-quind/assets/79119575/69762b89-fce0-414c-a8de-e4060c7ee419)
