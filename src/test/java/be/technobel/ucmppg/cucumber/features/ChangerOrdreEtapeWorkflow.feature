# language: fr

  @changerOrdre
  Fonctionnalité: Changer l'ordre d'une étape du workflow
    Test du changement d'ordre entre les étapes du workflow tout en respectant les contraintes de droit et permissions
    Contexte: situation de base
      Etant donné un droit de changer l'ordre d'une étape workflow d'un projet
      Etant donné un utilisateur createur de projet mail:empereur@empire.un,pseudo:empereur,motDePasse:Ordre66!
      Etant donné un projet "EtoileDeLaMort" créé par ce dernier dont "le but est d'instaurer la peur dans la galaxie" avec un role "empereur" et un role "sith" possédant le droit ainsi qu'un role "stormtrooper" ne le possédant pas
      Etant donné une liste d'étapes workflow avec leurs numero d'ordre :
      |ToDo|1|
      |Doing|2|
      |Test|3|
      |Done|4|


    Scénario: Un membre de projet qui a le droit va changer l'ordre de deux étapes
      Etant donné un nouvel utilisateur mail:sith@empire.un,pseudo:DarkVador,motDePasse:Ordre66!
      Et cet utilisateur participe au projet avec le role "sith"
      Quand l'utilisateur veut changer l'ordre de l'étape "Test" en ordre 2
      Alors l'ordre des étapes devient
        |ToDo|1|
        |Doing|3|
        |Test|2|
        |Done|4|

    Scénario: Un membre de projet qui a le droit va changer l'ordre d'une étape au début en fin
      Etant donné un nouvel utilisateur mail:sith@empire.un,pseudo:DarkVador,motDePasse:Ordre66!
      Et cet utilisateur participe au projet avec le role "sith"
      Quand l'utilisateur veut changer l'ordre de l'étape "ToDo" en ordre 4
      Alors l'ordre des étapes devient
        |ToDo|4|
        |Doing|1|
        |Test|2|
        |Done|3|

    Scénario: Un membre de projet qui a le droit va changer l'ordre d'une étape à la fin
      Etant donné un nouvel utilisateur mail:sith@empire.un,pseudo:DarkVador,motDePasse:Ordre66!
      Et cet utilisateur participe au projet avec le role "sith"
      Quand l'utilisateur veut changer l'ordre de l'étape "Test" en ordre 1
      Alors l'ordre des étapes devient
        |ToDo|2|
        |Doing|3|
        |Test|1|
        |Done|4|









