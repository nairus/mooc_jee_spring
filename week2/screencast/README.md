# Screencast

[vidéo](https://www.youtube.com/watch?v=hUk4JXJTmjM)

- SC07 :  
  Connectez vous à une base avec JDBC en utilisant [ce contenu](myapp).

## Pense-bête

### Compilation et exécution de l'application

1. Compilation des sources
   On peut compiler toutes les sources en compilant la classe de test.  
   L'option `-d` indique le répertoire où ranger les classes compilées.  
   L'option `-classpath` indique où se trouve les sources à compiler.

   ```bash
       C:\> cd C:\java\tomcat-8.5.34\webapps\myapp\WEB-INF\src
       C:\...\src> javac -d ..\classes -classpath ..\src person\PersonTest.java
   ```

1. Ajouter le JAR pour SQLite

   Faire un répertoire `lib` dans `WEB-INF` et ajouter le `jar` pour le `JDBC SQLite`.

1. Exécution de la classe de test
   L'option `-cp` permet d'indiquer le classpath.

   ```bash
        C:\...\src> java -cp ..\classes;..\lib\sqlite-jdbc-3.20.0.jar person.PersonTest
   ```

1. Compilation et exécution de la servlet

   - Compilation de la servlet

   ```bash
        C:\...\src> javac -d ..\classes -cp "..\classes;C:\java\tomcat-8.5.34\lib\servlet-api.jar" person\PersonServlet.java
   ```

   - Exécution de tomcat

   ```bash
        C:\...\src> cd C:\java\tomcat-8.5.34\bin
        C:\...\bin> catalina.bat run
   ```
