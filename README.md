# 🚀 Servicio de Autenticación con Spring Boot

Este servicio permite la autenticación de usuarios utilizando **Spring Boot**, **Feign Client** y **PostgreSQL**, consumiendo el servicio de **DummyJSON**.

---

## 📌 Instrucciones de Ejecución

Para ejecutar el servicio correctamente:

1. **Conectar a una base de datos PostgreSQL**
    - Configura las credenciales en el archivo `application.properties` ubicado en `src/main/resources`.

2. **Requisitos previos**
    - Tener **Maven** instalado.
    - Contar con **JDK 21**.

3. **Configurar la base de datos**
    - Crear la tabla `login_log` en PostgreSQL con el siguiente **query**:

```sql
CREATE TABLE login_log (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    login_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    access_token TEXT NOT NULL UNIQUE,
    refresh_token TEXT NOT NULL UNIQUE
);
```
## 🔑 Usuarios y Contraseñas de Prueba
Se pueden utilizar cualquier usuario de DummyJSON, pero estos están validados:

| Usuario | Password     |
|---------|--------------|
| michaelw | michaelwpass | 
| sophiab | sophiabpass  | 
| emilys | emilyspass   | 

## Ejemplos cURL
```sh
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "sophiab",
    "password": "sophiabpass"
}'
```

```sh
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "michaelw",
    "password": "michaelwpass"
}'
```

## Explicación del Guardado

El guardado del log de login es bastante simple:

1. **La entidad LoginLog contiene los mismos campos que cuando se consume el servicio de DummyJSON.**

2. **Se usa save(DATOS) apuntando al LoginLogRepository.**

3. **En la entidad LoginLog, los tokens deben almacenarse como TEXT, ya que si usas VARCHAR(255), podrías generar un error debido a la longitud del token.**

> 📌 Importante: Asegúrate de que access_token y refresh_token estén como TEXT en la base de datos para evitar problemas de almacenamiento.