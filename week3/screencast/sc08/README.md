# Note pour Screencast 08 - Maven / Log4j

## Utilisation de Maven

D'abord, il faut aller dans le répertoire du projet:

```bash
C:\> d:
D:\> cd Users\Utilisateur\Documents\nico\projects\mooc\java\nairus_jee_spring_2018\week3\screencast\sc08
```

1. Nettoyer les fichiers compilés

   Si les sources ont déjà été compilées:

   ```bash
   D:\Users\...\sc08> mvn clean
   ```

1. Compilation

   ```bash
   D:\Users\...\sc08> mvn compile
   ```

1. Générer un package

   ```bash
   D:\Users\...\sc08> mvn package
   ```

1. Installer un package dans le repo local

   ```bash
   D:\Users\...\sc08> mvn install
   ```

1. Installer de nouvelles dépendances

   Des librairies externes sont téléchargeables depuis le dépôt (repository) communautaire de [Maven](https://mvnrepository.com).
   Il est possible d'avoir un repository privé pour y déposer des librairies communes.

1. Résoudre les dépendances

   ```bash
   D:\Users\...\sc08> mvn dependency:resolve
   ```

1. Afficher l'arbre des dépendances

   ```bash
   D:\Users\...\sc08> mvn dependency:tree
   ```

1. Exécuter une classe principale

```bash
   D:\Users\...\sc08> mvn exec:java -Dexec.mainClass=App
```
