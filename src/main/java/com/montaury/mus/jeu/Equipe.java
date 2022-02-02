package com.montaury.mus.jeu;

import com.montaury.mus.console.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
  private List<Joueur> listeDesJoueurs;

  public Equipe(Joueur joueur1) {
    this.listeDesJoueurs = new ArrayList<>();
    this.listeDesJoueurs.add(joueur1);
  }

  public Equipe(Joueur joueur1, Joueur joueur2) {
    this.listeDesJoueurs.add(joueur1);
    this.listeDesJoueurs.add(joueur2);
  }

  public List<Joueur> getListeDesJoueurs() {
    return this.listeDesJoueurs;
  }
}
