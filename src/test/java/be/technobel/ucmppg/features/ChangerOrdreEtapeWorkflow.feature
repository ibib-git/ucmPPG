# language: fr

  Fonctionnalité: Changer l'ordre d'une étape du workflow
    Test du changement d'ordre entre les étapes du workflow tout en respectant les contraintes de droit et permissions
    Contexte: situation de base
      Etant donné un droit de changer l'ordre de 2 étapes workflow d'un projet
      Et un utilisateur createur de projet mail:empereur@empire.un,pseudo:empereur,motDePasse:Ordre66!
      Et un projet "EtoileDeLaMort" créé par ce dernier dont "le but est d'instaurer la peur dans la galaxie" avec un role "empereur" et un role "sith" possédant le droit ainsi qu'un role "stormtrooper" ne le possédant pas

      Scénario: Un membre de projet qui a le droit va changer l'ordre de 2 colonnes
        Etant donné un nouvel utilisateur mail:sith@empire.un,pseudo:DarkVador,motDePasse:Ordre66!
        Et cet utilisateur participe au projet avec le role "sith"
        Quand l'utilisateur veut changer l'ordre de 2 étapes
        Alors l'ordre des colonnes est inversé

    Scénario: L'utilisateur ne possède pas le droit pour le faire
      Etant donné un utilisateur membre mail:moff@empire.un,pseudo:Tarkin,motDePasse:Ordre66! de ce projet mais dont le role ne possède pas le droit
      Quand l'utilisateur veut changer l'ordre de 2 étapes
      Alors l'ordre des colonnes reste inchangé

    Scénario: L'utilisateur possède le droit pour le faire
      Mais le projet compte moins de 2 étapes workflow
      Alors un message d'erreur est envoyé

    Scénario: L'utilisateur ne participe pas à ce projet
      Alors un message d'erreur est envoyé







