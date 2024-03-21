# Taller 7 AREP - Brian Camilo Fajardo Sanchez

En este taller implementaremos una arquitectura segura en todos sus niveles, creando servidores HTTPS lo que añadirá 
integridad, aspectos de autorización y confidencialidad a nuestra arquitectura. Además, para asegurar una autenticación 
bien construida se añadió una funcionalidad de login donde las contraseñas se guardan y comparan con sus hashes.


## Iniciando

Estas instrucciones le permitirán disponer de una copia del proyecto en su equipo local para fines de desarrollo y pruebas.


### Prerrequisitos

* Git 
* Java
* Maven

Si aún no cuenta con las tecnologías necesarias aquí hay unos videotutoriales que pueden ser de utilidad.

* Git: https://www.youtube.com/watch?v=4xqVv2lTo40
* Java: https://www.youtube.com/watch?v=BG2OSaxWX4E
* Maven: https://www.youtube.com/watch?v=1QfiyR_PWxU

### Instalando el proyecto

Para correr el proyecto tienes que iniciar primero docker en tu máquina

Luego, clona el proyecto usando el siguiente comando

```
git clone https://github.com/briancfajardo/Taller7-AREP.git
```

Luego muévete al directorio creado y desde ahi ejecuta este comando

```
mvn clean compile
```

Posterior a ellos ejecuta los siguientes comandos para iniciar las dos instancias de la aplicación

```
mvn exec:java -Dexec.mainClass="org.edu.eci.arep.allsecureweb.login.SecureLogin"
```
```
mvn exec:java -Dexec.mainClass="org.edu.eci.arep.allsecureweb.securesparkapp.SecureFacade"
```

Podrás entrar a http://localhost:37000/ y deberías ver lo siguiente:

![pagInicial](https://github.com/briancfajardo/AREP-Taller6/assets/80064378/c94489e7-6bd4-42d7-80ce-41d34c1ec3ec)

Las credenciales correctas que hay son las siguientes:

|Usuario|Contraseña|
|-------|----------|
|Camilo|123456|
|Jaider|123456|

Luego de escribir las credenciales se debería ver de la siguiente manera:

## Corriendo test

### Test de integración

Para probar que el desarrollo de la aplicación fuera correcto sé probo cada funcionalidad en ella corriendo, para ello 
hicimos dos pruebas, la primera con un usuario que no existe y la segundo con un usuario que en efecto existe:

Usuario no existente:

Usuario existente:



![EjemplodeUso](https://github.com/briancfajardo/AREP-Taller6/assets/80064378/5f332551-de53-4b22-8072-2ce3786935bf)


## Documentacion

Para poder ver la documentación del proyecto desde el terminal y ubicado en el directorio del proyecto debe ejecutar el 
siguiente comando:

```
mvn javadoc:javadoc
```

En la siguiente ruta se encuentra un archivo (index.html) generado con la documentación del proyecto, el cual puede ser 
abierto mediante un navegador web 

```
./target/site/apidocs/
```

## Construido con

* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [OMDAPI](https://www.omdbapi.com) - API externa de consulta

## Version

1.0-SNAPSHOT

## Autores

Brian Camilo Fajardo Sanchez - [briancfajardo](https://github.com/briancfajardo)

## Licencia

GNU General Public License family

## Diseño

Para simular desde un mismo proyecto la arquitectura propuesta se separaron por paquetes los componentes, un componente 
de fachada encargado de mostrar la página web y de hacer la conexión con el componente del login, y un paquete de login 
que contiene toda la lógica del login.

## Arquitectura

La arquitectura de este taller es presentada con el siguiente diagrama:

![arquitectura](https://github.com/briancfajardo/AREP-Taller6/assets/80064378/e58e18bb-79aa-4286-b4d2-69d17a13ddda)


En donde se especifican los siguientes componentes:

- Browser: Representa al cliente 
- LoginService: En la arquitectura que se creó en el taller este componente corresponde con la fachada del servidor 
- Otherservice: En la arquitectura que se creó en el taller este componente corresponde con el login

## Extensibilidad

La arquitectura de seguridad se podría expandir añadiendo más módulos conectados al módulo principal del login como 
en una especie de micro servicios, cada uno manteniendo los estándares de seguridad que hemos trabajado.

### Despliegue

Además, el proyecto java fue desplegado de 2 máquinas virtuales de AWS:

## Video

https://github.com/briancfajardo/AREP-Taller6/assets/80064378/d87e4aa0-a7ce-4970-ab56-9f8b59a8ee55

## Agradecimientos

* Al profesor de Arquitecturas empresariales, Daniel Benavides
