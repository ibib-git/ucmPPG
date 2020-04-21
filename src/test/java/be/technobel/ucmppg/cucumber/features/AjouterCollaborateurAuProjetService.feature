# language: fr
@ajouterCollabo
Fonctionnalité: Ajouter Collaborateur au projet
  Contexte: blabla
    Etant donné un utilisateur
    Et un projet
    Quand j'ajoute l'utilisateur au projet

  Scénario: la liste des projets de l'utilisateur est bien mise à jour
    Alors l'utilisateur possède une référence vers le projet

   Scénario: la liste des membres est bien mise à jour
    Alors le projet possède une référence vers le membre
