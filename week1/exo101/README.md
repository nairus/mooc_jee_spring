# Exercice 1/01

<<<<<<< HEAD
* Copier ce répertoire dans "webapps" de Tomcat et ouvrir une console à cet endroit
* Compléter le code de la servlet qui se trouve dans WEB-INF/classes.  
  Le source est volontairement ici pour être compilé facilement en ligne de commande.  
  Voir [Cette page](http://www.movable-type.co.uk/scripts/latlong.html) pour la formule de calcul de la distance de "haversine"
* Compiler le code source de la servlet,
  pour cela ajouter "servlet-api.jar" dans le classpath.
  Ce fichier est disponible dans le répertoire "lib" de Tomcat.
* Lancer Tomcat et ouvrir l'URL suivante dans un navigateur http://localhost:8080/exo101/
* Saisir les coordonnées de deux points

En guise de contrôle, saisir les coordonnées suivantes :
* Lille : 50.63722, 3.063333
* Marseille : 43.29639, 5.37

La distance retournée doit être : 834,7 km
=======
- Copier ce répertoire dans "webapps" de Tomcat et ouvrir une console à cet endroit
- Compléter le code de la servlet qui se trouve dans WEB-INF/classes.  
  Le source est volontairement ici pour être compilé facilement en ligne de commande.  
  Voir [Cette page](http://www.movable-type.co.uk/scripts/latlong.html) pour la formule de calcul de la distance de "haversine"
- Compiler le code source de la servlet,
  pour cela ajouter "servlet-api.jar" dans le classpath.
  Ce fichier est disponible dans le répertoire "lib" de Tomcat.
- Lancer Tomcat et ouvrir l'URL suivante dans un navigateur http://localhost:8080/exo101/
- Compléter l'URL en fonction du code de la servlet
- Saisir les coordonnées de deux points

En guise de contrôle, saisir les coordonnées suivantes :

- Lille : 50.63722, 3.063333
- Marseille : 43.29639, 5.37

La distance retournée doit être : 834,7 km

## Pense bête

- Compilation de la servlet:

```bash
C:\> cd C:\java\tomcat-8.5.34\webapps
C:\java\tomcat-8.5.34\webapps> javac -cp C:\java\tomcat-8.5.34\lib\servlet-api.jar exo101\WEB-INF\classes\MyServlet.java
```

- Lancement de tomcat:

Il faut relancer tomcat à chaque re-compilation de la servlet:

```bash
C:\> cd C:\java\tomcat-8.5.34\bin\
C:\java\tomcat-8.5.34\bin> catalina.bat run
```
>>>>>>> merge_brancg
