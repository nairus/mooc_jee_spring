# Exercice 1/02

- unzip week0/assets.zip dans ce répertoire
- copier ce répertoire dans le répertoire webapps de Tomcat
- compiler AuthServlet.java
- lancer Tomcat
- compléter AuthServlet, auth.jsp, welcome.jsp de façon à ce que
  - on ne puisse s'authentifier qu'avec admin@foo.com/admin
  - un message d'erreur s'affiche si l'authentification échoue
  - on ne puisse pas accéder à welcome.jsp sans authentification
  - lorsque l'on se déconnecte avec le lien correspondant présent sur la page welcome.jsp, le formulaire d'authentification s'affiche de nouveau ainsi qu'un message confirmant la déconnexion

## Pense bête

- Compilation de la servlet:

```bash
C:\> cd C:\java\tomcat-8.5.34\webapps
C:\...\webapps> javac -cp C:\java\tomcat-8.5.34\lib\servlet-api.jar exo102\WEB-INF\classes\AuthServlet.java
```

- Lancement de tomcat:

Il faut relancer tomcat à chaque re-compilation de la servlet:

```bash
C:\> cd C:\java\tomcat-8.5.34\bin\
C:\...\bin> catalina.bat run
```
