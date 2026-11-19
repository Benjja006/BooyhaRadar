# Statistics Service

## Qué hace

Registra y consulta K/D, daño promedio, partidas y kills por jugador. Al actualizar estadísticas, guarda los valores anteriores en un historial.

## Qué no hace

No obtiene estadísticas directamente desde Free Fire ni comprueba que el jugador exista en Player Service. El registro se realiza manualmente mediante la API.

## Endpoints

- `POST /estadisticas` — registrar estadísticas para un jugador.
- `GET /estadisticas` — listar estadísticas.
- `GET /estadisticas/{id}` — consultar estadísticas por ID.
- `GET /estadisticas/jugador/{jugadorId}` — consultar por jugador.
- `GET /estadisticas/jugador/{jugadorId}/historial` — consultar el historial.
- `PUT /estadisticas/jugador/{jugadorId}` — actualizar estadísticas.
- `DELETE /estadisticas/{id}` — eliminar estadísticas.
