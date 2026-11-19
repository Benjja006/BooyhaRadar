# Scouting Service

## Qué hace

Busca jugadores por país, edad, rol, experiencia y estadísticas. Consulta Player Service y, cuando se usan filtros estadísticos, Statistics Service. También permite guardar y quitar jugadores favoritos por usuario.

## Qué no hace

No administra perfiles, organizaciones ni cuentas. Tampoco envía mensajes ni contacta a los jugadores. Los favoritos guardan IDs; el servicio no valida que el usuario corresponda a una organización.

## Endpoints

- `GET /scouting/jugadores` — buscar jugadores. Filtros opcionales: `pais`, `edadMinima`, `edadMaxima`, `rol`, `experiencia`, `kdMinimo`, `danoMinimo`, `partidasMinimas`.
- `POST /favoritos/{usuarioId}/{jugadorId}` — guardar un favorito.
- `GET /favoritos/{usuarioId}` — listar favoritos de un usuario.
- `DELETE /favoritos/{usuarioId}/{jugadorId}` — quitar un favorito.
