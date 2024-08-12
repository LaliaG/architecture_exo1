### Explication Très Détaillée de la Démo

Cette démo illustre comment utiliser les aspects en Java avec Spring AOP (Aspect-Oriented Programming) pour intercepter et manipuler l'exécution des méthodes dans une application Spring Boot. AOP est une technique puissante pour séparer les préoccupations transversales, telles que le logging, la sécurité, et la gestion des transactions, de la logique métier principale. Voici une explication détaillée de chaque partie de la démo.

#### 1. Fichier `pom.xml`
Le fichier `pom.xml` est la configuration du projet Maven. Il spécifie les dépendances nécessaires et configure le build du projet.

- **Parent Dependency:**
  ```xml
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>3.3.2</version>
      <relativePath/>
  </parent>
  ```
  Le projet hérite des configurations définies dans `spring-boot-starter-parent`, ce qui simplifie la gestion des versions des dépendances et des plugins.

- **Dépendances:**
  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-aop</artifactId>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
  </dependencies>
  ```
  - `spring-boot-starter-web` : Nécessaire pour créer des applications web avec Spring Boot.
  - `spring-boot-starter-aop` : Fournit le support AOP pour Spring, permettant la création et l'utilisation d'aspects.
  - `spring-boot-starter-test` : Inclut des outils pour tester l'application, utilisé pour les tests unitaires.

#### 2. Classe `DemoService`
```java
@Service
public class DemoService {

    public void method() {
        System.out.println("Log application metier method dans demoService");
    }

    public void method2() {
        // Logique
    }

    public String methodWithResult() {
        return "result";
    }

    public void methodWithTryCatch() {
        System.out.println("Logique métier");
    }
}
```
- **Service Spring**: `@Service` indique que cette classe est un service Spring, un composant gérant la logique métier.
- **Méthodes**:
  - `method()` : Exécute une tâche métier simple et log l'information.
  - `method2()` : Une méthode vide pour illustrer un autre point d'interception potentiel.
  - `methodWithResult()` : Retourne une chaîne de caractères, illustrant l'interception des valeurs de retour.
  - `methodWithTryCatch()` : Contient un bloc de logique métier avec un try-catch pour montrer l'interception des exceptions.

#### 3. Classe `DemoController`
```java
@Controller
@RequestMapping("/")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/")
    public String get() {
        demoService.method();
        demoService.method2();
        demoService.methodWithResult();
        demoService.methodWithTryCatch();
        return "Fin méthode";
    }
}
```
- **Controller Spring**: `@Controller` indique que cette classe gère les requêtes HTTP.
- **Injection de dépendance**: `DemoService` est injecté via le constructeur.
- **Mapping**:
  - `@GetMapping("/")`: Gère les requêtes HTTP GET à la racine `/`.
  - La méthode `get()` appelle différentes méthodes du `DemoService` pour illustrer les interceptions AOP.

#### 4. Classe `DemoAspectAdvice`
```java
@Component
@Aspect
public class DemoAspectAdvice {

    @Around("execution(* com.example.demo_aspect.service.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("Around cut");
            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println(args);
            proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            System.out.println("Catch exception with around cut");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
```
- **Aspect Spring**: `@Aspect` indique que cette classe définit un aspect, une unité de logique transversale.
- **Composant Spring**: `@Component` permet à Spring de détecter et de gérer cette classe comme un bean.
- **Pointcut `@Around`**:
  - `execution(* com.example.demo_aspect.service.*.*(..))`: Définit un pointcut interceptant toutes les méthodes de toutes les classes du package `service`.
  - **Méthode `around()`**: 
    - `ProceedingJoinPoint` : Permet de contrôler l'exécution de la méthode interceptée.
    - **Proceeds**: `proceedingJoinPoint.proceed()` exécute la méthode réelle.
    - **Gestion d'exceptions**: Le bloc try-catch montre comment intercepter les exceptions durant l'exécution de la méthode.

### Résolution de l'Exercice : Implémentation des Aspects pour `BookService`

Pour cet exercice, nous devons implémenter deux aspects dans une application Spring Boot :
1. **LoggingAspect** : Log l'entrée et la sortie des méthodes du service `BookService`.
2. **PerformanceAspect** : Mesure le temps d'exécution des méthodes du service `BookService`.

#### Étape 1 : Création du `BookService`
Tout d'abord, nous devons créer un service pour gérer les livres.

```java
package com.example.library.service;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    public void createBook(String title, String author) {
        System.out.println("Creating a book: " + title + " by " + author);
    }

    public void deleteBook(Long bookId) {
        System.out.println("Deleting book with ID: " + bookId);
    }

    public String getBook(Long bookId) {
        return "Fetching book with ID: " + bookId;
    }
}
```

#### Étape 2 : Création de `LoggingAspect`
Cet aspect loguera l'entrée et la sortie des méthodes du `BookService`.

```java
package com.example.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.example.library.service.BookService.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        System.out.println("Entering method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: ");
        for (Object arg : joinPoint.getArgs()) {
            System.out.println(" - " + arg);
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.library.service.BookService.*(..))", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        System.out.println("Exiting method: " + joinPoint.getSignature().getName());
        if (result != null) {
            System.out.println("Return value: " + result);
        }
    }
}
```

- **`@Before`**: Intercepte l'entrée dans chaque méthode de `BookService`.
- **`@AfterReturning`**: Log la sortie des méthodes, incluant la valeur retournée si disponible.

#### Étape 3 : Création de `PerformanceAspect`
Cet aspect mesurera le temps d'exécution des méthodes.

```java
package com.example.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {

    @Around("execution(* com.example.library.service.BookService.*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();  // exécute la méthode réelle
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("Time taken by " + proceedingJoinPoint.getSignature().getName() + " is " + timeTaken + "ms");
        return result;
    }
}
```

- **`@Around`**: Entoure l'exécution des méthodes pour mesurer leur durée.
- **Calcul du temps**: Le temps est calculé en millisecondes et logué.

### Résumé

- **LoggingAspect**: Fournit un logging détaillé de chaque méthode du `BookService`, enregistrant les entrées, les arguments et les valeurs de retour.
- **PerformanceAspect**: Mesure et log le temps d'exécution des méthodes du `BookService`.

Ces deux aspects aident à surveiller et maintenir l'application en fournissant des informations essentielles sur le comportement du service `BookService`.

Bien sûr! Prenons le temps de comprendre la démo du cours en profondeur, puis nous passerons à une explication détaillée de la résolution de l'exercice.

### Démo du Cours : Utilisation de Spring AOP

#### 1. Introduction à l'AOP (Aspect-Oriented Programming)
L'AOP (programmation orientée aspect) est une technique qui permet de séparer les préoccupations transversales (comme le logging, la sécurité, la gestion des transactions, etc.) de la logique métier principale dans une application. Dans une application Spring Boot, AOP est souvent utilisé pour intercepter les appels de méthode afin d'ajouter des comportements supplémentaires avant, après, ou autour de l'exécution de la méthode réelle.

#### 2. Les Composants Clés de la Démo

##### 2.1. `pom.xml` : Configuration du Projet
Le fichier `pom.xml` est essentiel dans un projet Maven. Il contient les dépendances et les plugins nécessaires pour construire et exécuter le projet.

- **Dépendances Principales** :
  - **`spring-boot-starter-web`** : Nécessaire pour créer des applications Web avec Spring Boot.
  - **`spring-boot-starter-aop`** : Nécessaire pour utiliser l'AOP avec Spring. Il permet de créer des aspects et de définir des comportements transversaux.
  - **`spring-boot-starter-test`** : Utilisé pour les tests unitaires.

Ces dépendances vous permettent d'utiliser Spring Boot, AOP et de faire des tests.

##### 2.2. `DemoService` : Service Gérant la Logique Métier
Le `DemoService` est une classe Java annotée avec `@Service`, ce qui indique à Spring qu'il s'agit d'un composant gérant la logique métier.

```java
@Service
public class DemoService {

    public void method() {
        System.out.println("Log application metier method dans demoService");
    }

    public void method2() {
        // Logique supplémentaire
    }

    public String methodWithResult() {
        return "result";
    }

    public void methodWithTryCatch() {
        try {
            System.out.println("Logique métier");
        } catch (Exception ex) {
            System.out.println("Logique exception");
        }
    }
}
```

- **`method()`**: Affiche un message de log.
- **`method2()`**: Méthode vide pour démonstration.
- **`methodWithResult()`**: Retourne une chaîne de caractères.
- **`methodWithTryCatch()`**: Une méthode qui gère des exceptions avec un bloc `try-catch`.

##### 2.3. `DemoController` : Contrôleur Gérant les Requêtes Web
Le `DemoController` est un contrôleur Spring qui reçoit des requêtes HTTP et appelle les méthodes du `DemoService`.

```java
@Controller
@RequestMapping("/")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/")
    public String get() {
        demoService.method();
        demoService.method2();
        demoService.methodWithResult();
        demoService.methodWithTryCatch();
        return "Fin méthode";
    }
}
```

- **`@Controller`** : Indique que cette classe est un contrôleur Spring.
- **`@GetMapping("/")`** : Associe la méthode `get()` à la route `/`, qui est déclenchée par une requête GET.

Quand une requête est faite à la racine (`/`), cette méthode appelle successivement toutes les méthodes du `DemoService`.

##### 2.4. `DemoAspectAdvice` : Aspect Définissant des Comportements Transversaux
L'aspect `DemoAspectAdvice` est un exemple d'utilisation d'AOP pour intercepter les appels de méthode dans le package `service`.

```java
@Component
@Aspect
public class DemoAspectAdvice {

    @Around("execution(* com.example.demo_aspect.service.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("Around cut");
            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println(args);
            proceedingJoinPoint.proceed();  // Exécution de la méthode interceptée
        } catch (Exception ex) {
            System.out.println("Catch exception with around cut");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
```

- **`@Aspect`** : Indique que cette classe est un aspect. Les aspects encapsulent les comportements transversaux (comme le logging).
- **`@Around`** : Cette annotation intercepte l'exécution des méthodes spécifiées par le pointcut (`execution(* com.example.demo_aspect.service.*.*(..))`). Cela signifie que toutes les méthodes de toutes les classes du package `service` seront interceptées.
- **`ProceedingJoinPoint`** : Représente la méthode interceptée, permet d'accéder à ses arguments, et d'exécuter la méthode réelle via `proceed()`.

**Fonctionnement :** Avant l'exécution d'une méthode dans `DemoService`, l'aspect affiche "Around cut", puis exécute la méthode réelle. Si une exception est levée, elle est attrapée et un message est affiché.

### Résolution de l'Exercice

#### Contexte de l'Exercice
Vous travaillez sur une application de gestion de bibliothèque. Vous devez ajouter des aspects pour améliorer la maintenance et la surveillance en loguant l'entrée et la sortie des méthodes, et en mesurant le temps d'exécution des méthodes du service `BookService`.

#### 1. Création de `BookService`
C'est un service qui gère les opérations de base sur les livres.

```java
package com.example.library.service;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    public void createBook(String title, String author) {
        System.out.println("Creating a book: " + title + " by " + author);
    }

    public void deleteBook(Long bookId) {
        System.out.println("Deleting book with ID: " + bookId);
    }

    public String getBook(Long bookId) {
        return "Fetching book with ID: " + bookId;
    }
}
```

- **`createBook`**: Crée un nouveau livre.
- **`deleteBook`**: Supprime un livre.
- **`getBook`**: Récupère un livre par son ID.

#### 2. Création de `LoggingAspect`
Cet aspect loguera l'entrée et la sortie des méthodes de `BookService`.

```java
package com.example.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.example.library.service.BookService.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        System.out.println("Entering method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: ");
        for (Object arg : joinPoint.getArgs()) {
            System.out.println(" - " + arg);
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.library.service.BookService.*(..))", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        System.out.println("Exiting method: " + joinPoint.getSignature().getName());
        if (result != null) {
            System.out.println("Return value: " + result);
        }
    }
}
```

- **`@Before`**: Intercepte l'entrée dans les méthodes de `BookService`. Il logue le nom de la méthode et ses arguments.
- **`@AfterReturning`**: Intercepte la sortie des méthodes de `BookService` et logue le nom de la méthode et la valeur retournée.

**Fonctionnement :** Chaque fois qu'une méthode de `BookService` est appelée, le `LoggingAspect` logue les détails de cette méthode avant son exécution (`@Before`) et après son exécution (`@AfterReturning`).

#### 3. Création de `PerformanceAspect`
Cet aspect mesurera et loguera le temps d'exécution des méthodes de `BookService`.

```java
package com.example.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {

    @Around("execution(* com.example.library.service.BookService.*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();  // Exécute la méthode réelle
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("Time taken by " + proceedingJoinPoint.getSignature().getName() + " is " + timeTaken + "ms");
        return result;
    }
}
```

- **`@Around`** : Entoure l'exécution des méthodes de `BookService`.
- **Calcul du temps** : Avant et après l'exécution de la méthode, l'heure actuelle est capturée. La différence entre ces deux temps donne la durée d'exécution de la méthode.

**Fonctionnement :** Chaque fois qu'une méthode de `BookService` est exécutée, le `PerformanceAspect` mesure le temps pris par cette

 méthode et le logue.

### Conclusion
L'AOP est un outil puissant pour ajouter des fonctionnalités transversales, comme le logging et la mesure de performance, sans modifier le code métier principal. Les aspects créés dans cet exercice permettent de surveiller efficacement le `BookService`, en loguant l'activité et en surveillant les performances, ce qui est crucial pour la maintenance et l'optimisation de l'application.

Analysons la correction fournie par votre formateur, en la comparant à la solution que nous avons discutée précédemment, pour mieux comprendre les différences et les choix techniques.

### 1. **Le fichier `pom.xml`**
- **Dépendances supplémentaires** :
  - **`spring-boot-starter-data-jpa`** : Cette dépendance est ajoutée pour permettre l'utilisation de JPA (Java Persistence API) pour la gestion des entités et des interactions avec la base de données.
  - **`com.h2database:h2`** : Cette dépendance est incluse pour utiliser la base de données H2, une base de données en mémoire utile pour le développement et les tests.

Ces ajouts indiquent que la solution de votre formateur intègre une gestion persistante des données à travers JPA, ce qui n'était pas présent dans l'exercice initial.

### 2. **Classe `Book` (Entité JPA)**
La correction inclut une entité `Book`, annotée avec `@Entity`, ce qui signifie que cette classe est mappée à une table dans une base de données relationnelle.

- **Champs de l'entité** :
  - `id` : Clé primaire, auto-générée avec la stratégie `GenerationType.IDENTITY`.
  - `name` et `author` : Champs représentant respectivement le nom du livre et l'auteur.

**Différence** : Dans la solution précédente, nous avions des méthodes métier sans stockage persistant. Ici, on a un modèle persistant avec une entité qui est gérée par une base de données.

### 3. **`BookRepository` (Interface Repository)**
La correction utilise une interface `BookRepository` qui étend `CrudRepository<Book, Integer>`. Cette interface fournit les méthodes CRUD (Create, Read, Update, Delete) de base sans avoir besoin de les implémenter manuellement.

- **`@Repository`** : Indique que cette interface est un composant Spring qui interagit avec la base de données.

**Différence** : L'approche précédente gérait les livres uniquement en mémoire via des objets Java, alors que cette correction gère les données de manière persistante via JPA.

### 4. **`BookService` (Service de gestion des livres)**
Le `BookService` dans cette correction fait appel au `BookRepository` pour persister, trouver, et lister les livres.

- **`findById`** : Recherche un livre par son ID. Si le livre n'est pas trouvé, une exception est lancée.
- **`save`** : Crée un nouveau livre si le nom dépasse trois caractères, sinon une exception est lancée.
- **`findAll`** : Retourne la liste de tous les livres.

**Différence** : Le service précédent gérait les opérations directement, tandis que celui-ci délègue les opérations CRUD à un repository, ce qui permet une séparation plus claire des responsabilités.

### 5. **`BookController` (Contrôleur REST)**
Le `BookController` gère les requêtes HTTP pour interagir avec les livres.

- **`@RestController`** : Indique que cette classe gère des requêtes REST et retourne les réponses en format JSON.
- **`@RequestMapping("books")`** : Toutes les routes gérées par ce contrôleur commencent par `/books`.
- **`@PostMapping`** : Crée un nouveau livre via une requête POST.
- **`@GetMapping`** : Récupère tous les livres ou un livre par ID via des requêtes GET.

**Différence** : Cette structure est similaire à celle précédente, mais maintenant les méthodes du contrôleur font appel à un service qui persiste les données.

### 6. **Aspect `LoggingAspect`**
Le `LoggingAspect` a été corrigé pour fournir des logs plus complets, avec des points de coupe (`pointcuts`) et des actions spécifiques (`advice`) à des moments précis :

- **`@Before`** : Logue les informations avant l'exécution des méthodes du `BookService`.
- **`@AfterReturning`** : Logue la valeur de retour après l'exécution réussie des méthodes du `BookService`.
- **`@AfterThrowing`** : Capture et logue les exceptions lancées par les méthodes du `BookService`.

**Différence** : Dans la correction, l'aspect ne se contente pas de logger l'entrée et la sortie des méthodes. Il capture aussi les exceptions et logue des détails supplémentaires sur les méthodes invoquées, ce qui rend les logs plus complets et plus utiles pour le débogage.

### Conclusion

**Différences Clés** :
1. **Persistance des Données** : La correction introduit la persistance des données via JPA, tandis que la solution initiale ne faisait que gérer des objets en mémoire.
2. **Separation des Couches** : La correction utilise un repository pour interagir avec la base de données, séparant ainsi la logique de persistance de la logique métier, ce qui est une bonne pratique de conception.
3. **Logging plus avancé** : L'aspect de logging est plus complet dans la correction, offrant une meilleure traçabilité et gestion des erreurs.

**Raisons de ces Changements** :
- **Professionnalisation** : La correction rend le projet plus proche de ce que l'on trouve dans les applications professionnelles, avec une gestion des données persistantes.
- **Modularité et Maintenance** : En séparant les préoccupations (persistance, service, logging), le code devient plus facile à maintenir, à tester, et à étendre.
- **Robustesse** : La gestion des exceptions et des logs détaillés permet une meilleure surveillance et un débogage plus efficace de l'application.

Cela montre une approche plus complète et professionnelle de l'application, mettant en avant des concepts importants comme la séparation des préoccupations, la persistance des données, et la robustesse du code via un logging avancé.

Analysons la suite de la correction avec l'introduction d'un **`PerformanceAspect`**. Cet aspect est conçu pour mesurer le temps d'exécution des méthodes du service `BookService`.

### 1. **Annotations et Composants**

- **`@Component`** : 
  - Indique que la classe `PerformanceAspect` est un composant Spring. Spring détecte et gère cette classe, l'intégrant au contexte de l'application.
  
- **`@Aspect`** :
  - Indique que cette classe contient des conseils (advice) et des points de coupe (pointcuts) pour les aspects transversaux de l'application, comme la performance ici.

### 2. **La Méthode `performanceAspect`**

#### **Signature de la Méthode**

```java
public Object performanceAspect(ProceedingJoinPoint joinPoint) throws Throwable
```

- **`ProceedingJoinPoint`** :
  - C'est une interface dans Spring AOP qui permet d'accéder à l'information sur la méthode qui est en train d'être interceptée, ainsi que de contrôler sa progression. 
  - Contrairement à un `JoinPoint` classique utilisé dans des aspects comme `@Before`, `@After`, etc., `ProceedingJoinPoint` est utilisé avec `@Around` pour permettre la continuation de l'exécution de la méthode interceptée.

- **`throws Throwable`** :
  - Indique que cette méthode peut lancer une exception, ce qui est nécessaire puisque `proceed()` peut potentiellement lancer n'importe quelle exception.

#### **Pointcut et Advice : `@Around`**

```java
@Around("execution(* com.example.correction_exercice_1.service.BookService.*(..))")
```

- **`@Around`** :
  - Le conseil `@Around` entoure l'exécution de la méthode ciblée. Il permet de définir du code à exécuter avant et après l'appel de la méthode, et même d'empêcher l'exécution ou de modifier le retour de la méthode.

- **`execution(* com.example.correction_exercice_1.service.BookService.*(..))`** :
  - Ce point de coupe capture toutes les méthodes de la classe `BookService`, quelle que soit leur signature. Il cible donc tous les appels de méthode au sein de ce service.

#### **Le Code à l'intérieur du conseil `@Around`**

```java
long startTime = System.currentTimeMillis();
Object result = joinPoint.proceed();
long endTime = System.currentTimeMillis();
System.out.println("Temps d'execution : " + (endTime - startTime) + " ms");
return result;
```

- **Mesure du Temps d'Exécution** :
  - **`startTime` et `endTime`** : Le temps est mesuré en millisecondes avant et après l'exécution de la méthode interceptée par `joinPoint.proceed()`.
  - **`joinPoint.proceed()`** : Cette ligne exécute la méthode d'origine (celle du `BookService`), permettant au conseil de mesurer précisément la durée de l'exécution.
  - **Calcul et Affichage du Temps d'Exécution** : Le temps total d'exécution est calculé en soustrayant `startTime` de `endTime` et est affiché dans la console.

- **Retour du Résultat Original** :
  - **`return result;`** : La méthode interceptée retourne son résultat d'origine après l'exécution du conseil.

### 3. **Utilité du `PerformanceAspect`**

Ce conseil `@Around` permet de mesurer de manière efficace le temps d'exécution de chaque méthode de `BookService`. Cela est crucial pour :

- **Optimisation** : Identifier les méthodes qui prennent trop de temps à s'exécuter et qui pourraient nécessiter une optimisation.
- **Surveillance** : Fournir des informations en temps réel sur les performances, ce qui est utile pour les diagnostics et la maintenance.

### **Différence avec le LoggingAspect**

- **LoggingAspect** : Logue des informations sur les appels de méthodes, les arguments, les résultats, et les exceptions.
- **PerformanceAspect** : Mesure et affiche le temps d'exécution des méthodes.

Ces deux aspects complètent la surveillance de l'application en offrant à la fois des logs détaillés et des informations de performance, ce qui rend l'application plus robuste et plus facile à maintenir.

### **Conclusion**

Le `PerformanceAspect` est un ajout essentiel pour surveiller les performances des services dans une application Spring. Il fournit une vue claire sur la rapidité des méthodes du `BookService`, ce qui peut aider les développeurs à identifier les goulots d'étranglement et à optimiser le code pour de meilleures performances. C'est un excellent exemple de l'utilisation d'AOP pour répondre à des préoccupations transversales sans encombrer le code métier.

### Explication de la Nouvelle Partie du Code

#### **1. Introduction des Annotations Personnalisées**

L'ajout clé ici est l'annotation personnalisée `@DemoAspectAnnotation` et son utilisation pour définir un point de coupe (`Pointcut`) spécifique.

##### **Classe `DemoAspectAdvice`**

Cette classe est un aspect (`@Aspect`) qui applique un conseil (`Advice`) à des méthodes en utilisant une annotation personnalisée.

##### **Annotation Personnalisée `@DemoAspectAnnotation`**

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoAspectAnnotation {
}
```

- **`@Target(ElementType.METHOD)`** : 
  - Spécifie que cette annotation peut être appliquée uniquement sur des méthodes.

- **`@Retention(RetentionPolicy.RUNTIME)`** : 
  - Indique que cette annotation sera disponible à l'exécution, ce qui est nécessaire pour qu'un aspect AOP puisse l'utiliser.

Cette annotation sera appliquée à des méthodes spécifiques pour lesquelles nous souhaitons exécuter du code transversal.

#### **2. Le Point de Coupe (`Pointcut`)**

```java
@Pointcut("@annotation(com.example.demo_aspect.annotation.DemoAspectAnnotation)")
public void customPointCut() {
}
```

- **`@Pointcut("@annotation(com.example.demo_aspect.annotation.DemoAspectAnnotation)")`** :
  - Ce point de coupe capture toutes les méthodes annotées avec `@DemoAspectAnnotation`.
  - Il permet de cibler uniquement les méthodes spécifiques marquées par cette annotation, au lieu de cibler toutes les méthodes d'un package ou d'une classe.

#### **3. Le Conseil Autour (`@Around`)**

```java
@Around("customPointCut()")
public Object around(ProceedingJoinPoint proceedingJoinPoint) {
    try {
        System.out.println("Around cut");
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println(args);
        Object result = proceedingJoinPoint.proceed();
        return result;
    } catch (Exception ex) {
        System.out.println("Catch exception with around cut");
    } catch (Throwable e) {
        throw new RuntimeException(e);
    } finally {
        return null;
    }
}
```

- **`@Around("customPointCut()")`** :
  - Ce conseil autour est appliqué aux méthodes ciblées par le `Pointcut` défini précédemment, c'est-à-dire toutes les méthodes annotées avec `@DemoAspectAnnotation`.

- **`ProceedingJoinPoint`** :
  - Comme expliqué précédemment, il permet d'exécuter la méthode interceptée tout en entourant son exécution avec du code avant et après.

- **Fonctionnement du Conseil** :
  - **Arguments** : Les arguments de la méthode sont récupérés et affichés.
  - **Exécution de la Méthode** : La méthode est ensuite exécutée avec `proceed()`.
  - **Résultat** : Le résultat de la méthode est renvoyé.

> **Remarque** : Le bloc `finally` retourne toujours `null`, ce qui n'est pas correct dans la pratique car il annule le retour de la méthode originale. Ce serait préférable de retourner le résultat `result` obtenu dans le bloc `try`.

### Partie 2 de l'Exercice

#### **Objectif**

Implémenter les aspects `Log` et `Performance` à l'aide d'annotations personnalisées.

#### **Étape 1 : Créer les Annotations Personnalisées**

1. **Annotation `@Log`**

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
}
```

2. **Annotation `@Performance`**

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Performance {
}
```

#### **Étape 2 : Modifier les Aspects pour Utiliser ces Annotations**

##### **Aspect `LoggingAspect`**

```java
@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(com.example.correction_exercice_1.annotation.Log)")
    public void logPointCut() {}

    @Before("logPointCut()")
    public void logGetMethodsInformations(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Nom méthode "+ methodName);
        System.out.println("Arguments : ");
        Arrays.stream(args).toList().forEach(System.out::println);
    }

    @AfterReturning(value = "logPointCut()", returning = "result")
    public void logAfterReturningResult(JoinPoint joinPoint, Object result) {
        System.out.println("Retour de la méthode "+ result);
    }

    @AfterThrowing(value = "logPointCut()", throwing = "ex")
    public void logAfterThrowingException(JoinPoint joinPoint, Exception ex) {
        System.out.println("Exception "+ ex);
    }
}
```

##### **Aspect `PerformanceAspect`**

```java
@Component
@Aspect
public class PerformanceAspect {

    @Pointcut("@annotation(com.example.correction_exercice_1.annotation.Performance)")
    public void performancePointCut() {}

    @Around("performancePointCut()")
    public Object measurePerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Temps d'execution : "+(endTime - startTime) + " ms");
        return result;
    }
}
```

#### **Étape 3 : Annoter les Méthodes dans `BookService`**

Appliquez ces annotations personnalisées aux méthodes du service `BookService`.

```java
@Service
public class BookService {

    @Log
    @Performance
    public Book findById(int id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()) {
            return bookOptional.get();
        }
        throw new RuntimeException("Book not found");
    }

    @Log
    @Performance
    public Book save(String name, String author) {
        if(name.length() > 3) {
            Book book = new Book();
            book.setAuthor(author);
            book.setName(name);
            return bookRepository.save(book);
        }
        throw new RuntimeException("Name must be gt 3");
    }

    @Log
    @Performance
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }
}
```

### **Conclusion**

En suivant cette approche, vous avez créé des aspects basés sur des annotations personnalisées pour le logging et la mesure des performances. Cette méthode est plus flexible, car elle permet de cibler spécifiquement les méthodes que vous souhaitez surveiller ou loguer, sans affecter toutes les méthodes d'une classe ou d'un package. Cela rend le code plus modulaire et maintenable.

Oui, la partie 2 de l'exercice a été résolue en suivant la même approche et logique que celles utilisées par ton formateur dans la démo que tu as partagée.

### Résumé de la Logique et de la Méthodologie Suivies

1. **Création d'Annotations Personnalisées** : 
   - Ton formateur a créé une annotation personnalisée (`@DemoAspectAnnotation`) pour cibler certaines méthodes dans les aspects. J'ai suivi cette logique en créant deux annotations personnalisées (`@Log` et `@Performance`) pour appliquer les aspects de logging et de performance.

2. **Définition des Pointcuts (`@Pointcut`)** : 
   - Ton formateur a utilisé un `Pointcut` pour cibler les méthodes annotées avec `@DemoAspectAnnotation`. J'ai appliqué la même technique en définissant des pointcuts pour cibler les méthodes annotées avec `@Log` et `@Performance`.

3. **Application des Conseils (`@Before`, `@AfterReturning`, `@Around`)** : 
   - Les conseils dans l'exercice sont appliqués de manière similaire à ceux de la démo. Par exemple, `@Around` est utilisé pour le conseil autour des méthodes annotées pour mesurer la performance, tout comme ton formateur l'a fait pour exécuter du code avant et après l'exécution de méthodes annotées.

4. **Intégration dans les Services** :
   - Comme ton formateur a montré comment annoter des méthodes spécifiques avec `@DemoAspectAnnotation`, j'ai suivi la même approche en annotant les méthodes du service `BookService` avec `@Log` et `@Performance`.

### Pourquoi cette Approche est Logique ?

- **Modularité** : Les annotations personnalisées permettent de contrôler précisément où les aspects sont appliqués, ce qui est une pratique recommandée pour éviter les effets secondaires indésirables dans l'application.

- **Réutilisabilité** : Ces annotations peuvent être utilisées ailleurs dans le projet, rendant l'application des aspects cohérente et standardisée.

- **Séparation des Préoccupations** : Cette approche suit le principe de séparation des préoccupations, où la logique métier (les services) est clairement séparée de la logique transversale (les aspects comme le logging et la performance).

En résumé, la résolution que j'ai fournie est en parfaite harmonie avec la méthodologie de ton formateur, en utilisant des annotations pour définir des aspects, ce qui permet une application ciblée et modulable de la logique transversale.