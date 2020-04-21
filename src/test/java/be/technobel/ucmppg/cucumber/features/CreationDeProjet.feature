# new feature
# Tags: optional

Feature: ProjetDTOCreationTest

Scenario: un Nom de projet
    Given je veux un Utilisateur
    And nom "bouffe"
    And descrip "olala"
    When le projet nom "bouffe"

Scenario: un Descrip de projet
    Given je veux un Utilisateur
    And nom "bouffe"
    And descrip "olala"
    When le projet desc "olala"

Scenario: un Createur
    Given je veux un Utilisateur
    And nom "bouffe"
    And descrip "olala"
    When le createur nom "baba"

Scenario: EtapeWork 1
    Given je veux un Utilisateur
    And nom "bouffe"
    And descrip "olala"
    When Etape "à faire"

Scenario: EtapeWork 2
    Given je veux un Utilisateur
    And nom "bouffe"
    And descrip "olala"
    When Etape "fini"

Scenario: EtapeWork 3
    Given je veux un Utilisateur
    And nom "bouffe"
    And descrip "olala"
    When Etape "en cours"

Scenario: Role 3
     Given je veux un Utilisateur
     And nom "bouffe"
     And descrip "olala"
     When role "administrateur"


Scenario: Role 1
     Given je veux un Utilisateur
     And nom "bouffe"
     And descrip "olala"
     When role "modérateur"

Scenario: Role 2
     Given je veux un Utilisateur
     And nom "bouffe"
     And descrip "olala"
     When role "membre"

