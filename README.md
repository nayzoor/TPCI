# Starter CI Jenkins + Maven (avec Maven Wrapper)

Ce starter contient **Maven Wrapper** → pas besoin d’installer Maven en local.
Il suffit d’avoir un **JDK 17+** puis de lancer :

```bash
./mvnw -B clean verify
```

Le wrapper téléchargera automatiquement :
- le **maven-wrapper.jar** (si absent), via `wrapperUrl` ;
- la distribution **Apache Maven 3.9.9**, via `distributionUrl` ;

## Prérequis
- **Java 17+** (`java -version`)
- Accès réseau au premier lancement (pour télécharger Maven)

## Commandes utiles
```bash
# Build complet (compile + tests + packaging + rapports)
./mvnw -B clean verify

# Activer l'échec si couverture < 80 % (quand des tests existeront)
./mvnw -B clean verify -P enforce-coverage
```

## Arborescence
```
.
├─ mvnw / mvnw.cmd
├─ .mvn/wrapper/maven-wrapper.properties
├─ pom.xml
├─ src/main/java/edu/coursic/demo/Calculator.java
└─ README.md
```

## À faire pour les étudiant·e·s
- Créer `src/test/java/edu/coursic/demo/CalculatorTest.java` avec des tests **JUnit 5**
- Vérifier la couverture **JaCoCo** (`target/site/jacoco/index.html`)
- (Plus tard) ajouter un `Jenkinsfile` pour la CI