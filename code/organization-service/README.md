# Organization Service

## Qué hace

Registra y gestiona perfiles de organizaciones. Las nuevas organizaciones quedan pendientes; se pueden aprobar o rechazar. Actualizar un perfil lo deja pendiente de nueva revisión.

## Qué no hace

No crea cuentas de usuario ni verifica que `administradorId` corresponda a una cuenta existente. La API no comprueba que quien cambia la aprobación sea administrador.

## Endpoints

- `POST /organizaciones` — registrar una organización.
- `GET /organizaciones` — listar organizaciones aprobadas.
- `GET /organizaciones/{id}` — consultar una organización.
- `PUT /organizaciones/{id}` — actualizar una organización.
- `PATCH /organizaciones/{id}/aprobacion?estado=APROBADA` — aprobar.
- `PATCH /organizaciones/{id}/aprobacion?estado=RECHAZADA` — rechazar.
- `DELETE /organizaciones/{id}` — eliminar una organización.
