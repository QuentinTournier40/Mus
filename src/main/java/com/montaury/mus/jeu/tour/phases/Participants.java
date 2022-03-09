package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class  Participants {
  private final List<Queue<Joueur>> listeParEquipes = new ArrayList<>();
  private List<Joueur> listeJoueurs = new ArrayList<>();

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
    listeParEquipes.add(equipe1);
    listeParEquipes.add(equipe2);
    this.listeJoueurs = listeJoueurs;
  }

  public boolean aucun() {
    return listeJoueurs.isEmpty();
  }

  public boolean equipeEstUnique() {
    return listeParEquipes.size() == 1;
  }
/*

  public boolean equipeEstUnique(){
    var uneEquipe = dansLOrdre.get(0).getMonEquipe();
    for(int i = 1; i < dansLOrdre.size(); i++){
      if(dansLOrdre.get(i).getMonEquipe() != uneEquipe){
        return false;
      }
    }
    return true;
  }
*/
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
