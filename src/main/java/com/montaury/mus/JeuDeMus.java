package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.Equipe;
import java.util.Scanner;

public class JeuDeMus {
  public static void main(String[] args) {
    System.out.print("Entrez votre nom: ");
    var nomJoueur = new Scanner(System.in).next();
    var joueurHumain = Joueur.humain(nomJoueur);
    var equipe1 = new Equipe(joueurHumain, Joueur.ordinateur("Ordinateur allié"));
    var equipe2 = new Equipe(Joueur.ordinateur("Ordinateur mechant 1"), Joueur.ordinateur("Ordinateur mechant 2"));

    var partie = new Partie(new AffichageEvenements(joueurHumain));
    var resultat = partie.jouer(new Opposants(equipe1, equipe2));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().getListeDesJoueurs().get(0).nom());
  }
}
