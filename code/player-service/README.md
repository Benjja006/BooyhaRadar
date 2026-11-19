# Player Service

## Qué hace

Gestiona perfiles competitivos: datos del jugador, roles de juego, experiencia, logros, contacto y disponibilidad. Cada perfil se asocia a una cuenta mediante `usuarioId`.

## Qué no hace

No gestiona cuentas, contraseñas ni estadísticas. Tampoco comprueba que `usuarioId` corresponda a una cuenta existente.

## Endpoints

- `POST /jugadores` — crear un perfil.
- `GET /jugadores` — listar perfiles.
- `GET /jugadores/{id}` — consultar un perfil.
- `PUT /jugadores/{id}` — actualizar un perfil.
- `DELETE /jugadores/{id}` — eliminar un perfil.
