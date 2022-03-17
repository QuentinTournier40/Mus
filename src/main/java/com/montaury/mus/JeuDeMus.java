package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.Equipe;
import java.util.Scanner;

public class JeuDeMus {
  public static void main(String[] args) {
    final String TYPE_JEU_1V1 = "1v1";
    final String TYPE_JEU_2V2 = "2v2";
    final String NOM_EQUIPE_BOT = "Les bots";

    System.out.print("Entrez votre nom : ");
    var nomJoueur = new Scanner(System.in).next();

    System.out.println("Jouer en mode 1v1 ou 2v2 ? (Saisir le mode) :");
    var typeJeu = new Scanner(System.in).next();

    while(!typeJeu.equals(TYPE_JEU_1V1) && !typeJeu.equals(TYPE_JEU_2V2)){
      System.out.println("Jouer en mode 1v1 ou 2v2 ? (Saisir le mode) :");
      typeJeu = new Scanner(System.in).next();
    }

    var joueurHumain = Joueur.humain(nomJoueur);
    System.out.println("Entrez votre nom d'équipe : ");
    var nomEquipeJoueur = new Scanner(System.in).next();

    Equipe equipe1 = null;
    Equipe equipe2 = null;

    if(typeJeu.equals(TYPE_JEU_1V1))
    {
      equipe1 = new Equipe(joueurHumain, nomEquipeJoueur);
      equipe2 = new Equipe(Joueur.ordinateur("Ordinateur mechant 1"), NOM_EQUIPE_BOT);
    }else {
      equipe1 = new Equipe(joueurHumain, Joueur.ordinateur("Ordinateur allié"), nomEquipeJoueur);
      equipe2 = new Equipe(Joueur.ordinateur("Ordinateur mechant 1"), Joueur.ordinateur("Ordinateur mechant 2"), NOM_EQUIPE_BOT);
    }

    var partie = new Partie(new AffichageEvenements(joueurHumain));
    var resultat = partie.jouer(new Opposants(equipe1, equipe2));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().getNom());
  }
}
