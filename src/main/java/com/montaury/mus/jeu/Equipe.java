package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
  private List<Joueur> listeDesJoueurs;
  private String nom;

  public Equipe(Joueur joueur1, String nomEquipe) {
    this.listeDesJoueurs = new ArrayList<>();
    this.listeDesJoueurs.add(joueur1);
    joueur1.setMonEquipe(this);
    this.nom = nomEquipe;
  }

  public Equipe(Joueur joueur1, Joueur joueur2, String nomEquipe) {
    this.listeDesJoueurs = new ArrayList<>();
    this.listeDesJoueurs.add(joueur1);
    this.listeDesJoueurs.add(joueur2);
    joueur1.setMonEquipe(this);
    joueur2.setMonEquipe(this);
    this.nom = nomEquipe;
  }

  public Equipe(){
  }

  public String getNom() {
    return nom;
  }

  public List<Joueur> getListeDesJoueurs() {
    return this.listeDesJoueurs;
  }
}
