# JAVA_login
Este proyecto muestra la creación de un login simple en Java y qué problemas de seguridad podríamos encontrar durante la implementación


## Preparación del entorno

Para la realización de este pequeño proyecto, he decidido basarme en el ejemplo 2 propuesto, un sistema de login utilizando Servlets. A partir de este sistema de login ha sido necesario realizar una serie de modificaciones para lograr su funcionamiento.

A continuación, se muestran las especificaciones del entorno de desarrollo:
- IDE: Eclipse IDE for Enterprise Java Developers (includes Incubating components). Version: 2020-09 (4.17.0)
- Java: Versión 8 , actualización 271 (compilación 1.8.0_271-b09)
- Servidor: Apache Tomcat 8.5
- Base de datos: SQLite version 3.10.1

En un primer momento, se prueba el sistema de login propuesto sin realizar nigún cambio en el código proporcionado, tras la obtención de una serie de errores relacionados con problemas de mapeo, los cuales causaban que una vez se enviaban los datos pulsando el botón de "_login_", la ejecución se paraba , ya que no sabía donde tenía que seguir. Para solucionar estos problemas, se ha añadido a los servlets **_Login_** y **_Welcome_** la anotacion @WebServelt(Servlet path), utilizada para la declaración de componentes Servlet en aplicaciones web. De esta manera el mapeo se produce de manera transparente y sin ningún problema. Además de estos cambios, en el archivo Validate, ha sido necesario modificar la conexión a la base de datos propuesta, ya que en el ejemplo se utiliza MySql y en mi caso he decidio utilizar SQLite.


## Análisis de la seguridad del sistema

En este apartado, se analizará el código propuesto para encontrar los posibles problemas de seguridad que se hayan podido introducir durante su programación. Para ello, se analiza el código implementado en cada uno de los archivos, además de realizar una evaluación general de la seguridad.

#### index.html
En este archivo podemos encontrar un error en el input del campo Password, ya que se está indicando que dicho input es de tipo texto. De esta manera, cuando el usuario introduce su contraseña, ésta se está mostrando en texto plano por pantalla, lo que introduce un problema de seguridad bastante grave, ya que cualquier persona o mecanismo que esté obervando o grabando nuestra pantalla puede hacerse con ella.

#### Login.java
En este archivo podemos encontrar un error cuando se produce la toma de datos del usuario y contraseña, ya que se está almacenando la contraseña en un String como un sólo bloque, permaneciendo la contraseña en ese objeto String hasta que se produzca un borrado de memoria. Como podemos ve, esto no es un mecanismo seguro para almacenar las contraseñas, ya que estos datos podrian permanecer mucho tiempo almacenados y tendríamos una serie de string literals con las diferentes contraseñas.

En los demás archivos del proyecto, no se ha encontrado ningún problema de seguridad en el código implamentado. A continuación, en el siguiente apartado, se propone la solución para estos problemas mencionados anteriormente

## Solución

En esta sección se propone una solución para los problemas de seguridad encontrados. Como en el caso anterior, indicaremos los cambios para cada uno de los archivos analizados y en los cuales se ha encontrado un error, además de indicar los cambios necesarios en otros archivos necesarios para el funcionamiento.

#### index.html
Para solucionar el error indicado anteriormente y que la contraseña no se muestre en claro por pantalla, es necesario indicar en el input que es de tipo password, de esta manera cuando se escribe en ese input, por pantalla cada caracter se muestra como un punto negro, imposibilitando saber la contraseña del usuario.

#### Login.java
Para solucionar el error de almacenar la password como un string, se han realizado una serie de cambios en el código. A continuación, se detallan dichos cambios, haciendo referencia al código modificado.

El primer cambio es almacenar la variable como un char [] , para ello, se cambia la linea de código propuesta en el ejemplo:
```
String pass = request.getParameter("pass");
```
Por la siguiente:
```
char [] pass = request.getParameter("pass").toCharArray();
```

El siguiente paso es cambiar el método _**checkUser**_ de la clase _Validate_ , ya que en el caso anterior recibía un String y en este caso le estamos pasando un char []. Para ello, en el archivo Validate.java, se realizan los siguientes cambios:

El primer cambio, es cambiar el variable que recibe el método, cambiando los siguiente:

```
public static boolean checkUser(String email,String pass)
```

Por:
```
public static boolean checkUser(String email,char [] pass)
```

A continuación, se convierte ese char [] a String para trabajar de una manera más sencilla. Posteriormente, al acabar el método, se inicializará este String a null para evitar porblemas de seguridad teniendo la contraseña almacenada en un String. La instrucción para la conversión del char [] a String es la siguiente:

```
String passw = String.valueOf(pass);
```
Al haber hecho esta modificación, es necesario también cambiar la viariable _pass_ en la linea 18 del _Validate.java_, ya que esa variable antes era el String que recibía el método y en este caso es el String que hemos creado a partir del char [] que se recibe. El cambio es el siguiente:

```
ps.setString(2, passw);
```
Por:
```
ps.setString(2, pass);
```






