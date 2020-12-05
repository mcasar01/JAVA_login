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
