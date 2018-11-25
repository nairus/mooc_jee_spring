# Exercice 2/01

Pour cet exercice, vous réutiliserez le contenu du répertoire webapp de la semaine précédente.  
Prenez soin d'avoir, au final, un contexte web nommé "exo201".

## Implémentation d'un DAO avec JDBC

- Téléchargez [JDBC SQLite Driver](https://oss.sonatype.org/content/repositories/releases/org/xerial/sqlite-jdbc/3.20.0/sqlite-jdbc-3.20.0.jar), et placez le dans le répertoire WEB-INF/lib
- Complétez [UserDaoSqlite](WEB-INF/classes/UserDaoSqlite.java)

A cette étape, vous devriez être en mesure de faire passer le "TestUserJDBC".  
Un script est fourni pour exécuter ces tests, sinon :

- téléchargez la librairie [junit](http://central.maven.org/maven2/junit/junit/4.12/junit-4.12.jar)
- téléchargez la librairie [hamcrest](http://central.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar)
- compilez les classes de tests avec la commande (sous unix il faut séparer les jar par `:`)  
  `javac -cp .;junit-4.12.jar;hamcrest-core-1.3.jar;../lib/commons-lang3-3.8.1.jar TestUserJDBC.java auth/SigninCheck.java`
- lancez les tests avec la commande  
  `java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar;../lib/sqlite-jdbc-3.20.0.jar org.junit.runner.JUnitCore TestUserJDBC`

Ceci suppose que vous ayez mis les librairies junit et hamcrest dans WEB-INF/classes et la librairie sqlite-jdbc dans WEB-INF/lib. Aussi, au moment de lancer les tests, vous devriez être dans WEB-INF/classes.

## Utilisation d'un DAO avec une servlet/jsp

Nous allons utiliser le DAO pour l'enregistrement des utilisateurs.

- Complétez RegisterServlet et utilisez le DAO
- Complétez register.jsp, et notamment :
  - pensez à traiter les différents cas d'erreur,
  - affichez des messages d'erreur en rapport avec le cas rencontré,
  - mettez en avant les champs du formulaire en erreur.

A cette étape, vous devriez pouvoir faire passer le test "auth/SigninCheck"

- Reprenez votre solution pour de l'exercice 1/02 en utilisant ce DAO pour la servlet d'authentification

## Astuces et pense-bête

1. Nettoyer les fichiers compilés (clean)

   ```bash
   del /s /q *.class
   ```

1. Compilation de la servlet

   ```bash
    C:\> cd C:\java\tomcat-8.5.34\webapps\exo201\WEB-INF\classes
    C:\..\WEB-INF\classes> javac -cp ".;C:\java\tomcat-8.5.34\lib\servlet-api.jar;C:\java\tomcat-8.5.34\lib\commons-lang3-3.8.1.jar;C:\java\tomcat-8.5.34\lib\commons-validator-1.6.jar" RegisterServlet.java
   ```
