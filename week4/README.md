# Semaine 4

- [exo401](exo401) aborde les contextes Spring et l'injection de dépendances.
- [exo402](exo402) est une mise en oeuvre de Spring dans une architecture web MVC.

## Notes perso

### Configuration de tomcat pour les webapps

Il est possible de dire à tomcat d'aller chercher des webapps ailleurs que dans sont répertoire `webapps` afin de simplifier les process de déploiements ou de développement (ex. centraliser les webapps dans un workspace eclipse):

Pour ce faire il faut éditer le fichier `server.xml` dans le répertoire `conf` de tomcat et d'ajouter ce genre de configuration:

```xml
<Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">

        <!-- SingleSignOn valve, share authentication between web applications
             Documentation at: /docs/config/valve.html -->
        <!--
        <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        -->

        <!-- Contexte à ajouter pour la webapp -->
        <Context docBase="C:\path\to\project\target\screencast-13-1.0-SNAPSHOT" path="sc13" reloaded="true" />

        <!-- Access log processes all example.
             Documentation at: /docs/config/valve.html
             Note: The pattern used is equivalent to using pattern="common" -->
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log" suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />

      </Host>
```

Ensuite il faut compiler le projet avec la commande suivante :

```bash
C:\path\to\project> mvn compile war:exploded
```

Cette commande permet de ne pas faire d'archive `.war` et déployer une appliweb compatible pour tomcat.
