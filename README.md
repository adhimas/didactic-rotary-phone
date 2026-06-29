# Scheduling app

## Design

Central to the app is the **Slot** entity. It represents a time window in a
user's calendar.

When a **Meeting** is created, the Slots of the corresponding participants are
updated, effectively marking them as busy.

The exact schema definition is available under the
[src/main/resources/db/migration/V1__Create_core_tables.sql](migration
directory).

## Instructions

Below are some useful commands.

```sh
$ docker compose up -d
$ docker compose logs --follow
$ docker compose down
$ docker volume rm <vol>
```

```sh
$ curl http://localhost:8080/actuator
$ curl http://localhost:8080/api/users/123/slots
$ curl -X POST -H "Content-Type: application/json" -d '{"start":"2026-06-30T10:00:00Z","end":"2026-06-30T11:00:00Z"}' http://localhost:8080/api/users/123/slots/
$ curl "http://localhost:8080/api/users/123/slots?rangeStart=2026-06-01T15:00:00Z&rangeEnd=2026-06-30T11:00:00Z"
$ curl -X POST -H "Content-Type: application/json" -d '{"title":"Test meeting"}' http://localhost:8080/api/users/123/slots/456/meeting
```

```sh
$ docker compose exec db psql -U user -d example
example=# \dt
example=# INSERT INTO users VALUES (123,'Jane');
example=# SELECT * FROM slots;
...
```
