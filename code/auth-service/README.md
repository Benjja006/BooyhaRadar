# Auth Service

## Qué hace

Registra e inicia sesión de usuarios. El login entrega un JWT con el ID y el rol de la cuenta. Los roles de registro son `PLAYER` y `ORGANIZATION_ADMIN`; `ADMIN` no se asigna desde el registro público.

## Qué no hace

No crea perfiles de jugadores ni de organizaciones. Esos perfiles se gestionan en sus propios servicios. La administración de usuarios requiere una cuenta `ADMIN`.

## Endpoints

- `POST /auth/register` — registrar una cuenta.
- `POST /auth/login` — iniciar sesión y obtener el JWT.
- `POST /usuarios` — crear un usuario (ADMIN).
- `GET /usuarios` — listar usuarios (ADMIN).
- `GET /usuarios/{id}` — consultar un usuario (ADMIN).
- `DELETE /usuarios/{id}` — eliminar un usuario (ADMIN).
