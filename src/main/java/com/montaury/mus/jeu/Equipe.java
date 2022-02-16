package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
  private List<Joueur> listeDesJoueurs;

  public Equipe(Joueur joueur1) {
    this.listeDesJoueurs = new ArrayList<>();
    this.listeDesJoueurs.add(joueur1);
    joueur1.setMonEquipe(this);
  }

  public Equipe(Joueur joueur1, Joueur joueur2) {
    this.listeDesJoueurs = new ArrayList<>();
    this.listeDesJoueurs.add(joueur1);
    this.listeDesJoueurs.add(joueur2);
    joueur1.setMonEquipe(this);
    joueur2.setMonEquipe(this);
  }

  public List<Joueur> getListeDesJoueurs() {
    return this.listeDesJoueurs;
  }
}
