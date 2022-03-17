package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class  Participants {
  private final List<Queue<Joueur>> listeParEquipes = new ArrayList<>();
  private List<Joueur> listeJoueurs;

  public Participants(List<Joueur> listeJoueurs) {
    Queue<Joueur> equipe1 = new LinkedList<>();
    Queue<Joueur> equipe2 = new LinkedList<>();
    Equipe equipeCourante = null;
    for(Joueur joueur : listeJoueurs){
      if(equipeCourante == null){
        equipeCourante = joueur.getMonEquipe();
        equipe1.add(joueur);
      }
      else if(equipeCourante == joueur.getMonEquipe()){
        equipe1.add(joueur);
      }
      else{
        equipe2.add(joueur);
      }
    }
    this.listeParEquipes.add(equipe1);
    this.listeParEquipes.add(equipe2);
    this.listeJoueurs = new ArrayList<>(listeJoueurs);
  }

  public boolean aucun() {
    return listeJoueurs.isEmpty();
  }

  public boolean equipeEstUnique() {
    for(Queue<Joueur> maPile : listeParEquipes) {
      if(maPile.size() == 0) {
        return true;
      }
    }
    return false;
  }

  public Joueur premier() {
    return listeJoueurs.get(0);
  }

  public Joueur adversaireDe(Joueur joueurParlant) {
    Joueur adversaire = null;
    if(listeJoueurs.get(0).getMonEquipe() == joueurParlant.getMonEquipe())
      adversaire = listeParEquipes.get(1).peek();
    else{
      adversaire = listeParEquipes.get(0).peek();
    }
    return adversaire;
  }

  public Iterable<Joueur> dansLOrdre() {
    return listeJoueurs;
  }

  public Participants retirer(Joueur joueur) {
    listeJoueurs.remove(joueur);
    return new Participants(listeJoueurs);
  }


}



