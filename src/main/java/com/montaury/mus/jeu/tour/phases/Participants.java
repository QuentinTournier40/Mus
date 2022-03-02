package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;

import java.util.ArrayList;
import java.util.List;

public class  Participants {
  private final List<Joueur> dansLOrdre;

  public Participants(List<Joueur> dansLOrdre) {
    this.dansLOrdre = dansLOrdre;
  }

  public boolean aucun() {
    return dansLOrdre.isEmpty();
  }

  public boolean estUnique() {
    return dansLOrdre.size() == 1;
  }

  public boolean equipeEstUnique(){
    var uneEquipe = dansLOrdre.get(0).getMonEquipe();
    for(int i = 1; i < dansLOrdre.size(); i++){
      if(dansLOrdre.get(i).getMonEquipe() != uneEquipe){
        return false;
      }
    }
    return true;
  }

  public Joueur premier() {
    return dansLOrdre.get(0);
  }

  /*
  public Joueur adversaireDe(Joueur joueurParlant, Choix choixFait) {
    Joueur adversaire;
    int indiceJoueurParlant = dansLOrdre.indexOf(joueurParlant);
    if(choixFait.est(TypeChoix.GEHIAGO)){
      if(indiceJoueurParlant + 1 == dansLOrdre.size()){
        adversaire = dansLOrdre.get(0);
      }else{
        adversaire = dansLOrdre.get(indiceJoueurParlant + 1);
      }
    }else{
      if(indiceJoueurParlant - 1 == -1){
        adversaire = dansLOrdre.get(dansLOrdre.size() - 1);
      }else{
        adversaire = dansLOrdre.get(indiceJoueurParlant - 1);
      }
    }
    return adversaire;
  }
*/

  public Joueur adversaireDe(Joueur joueurParlant, Choix choixFait) {
    Joueur adversaire = null;
    int indiceJoueurParlant = dansLOrdre.indexOf(joueurParlant);
    switch(indiceJoueurParlant){
      case 0:
        if(joueurParlant.getMonEquipe() != dansLOrdre.get(1).getMonEquipe()){
          adversaire = dansLOrdre.get(1);
        }
        else{
          adversaire = dansLOrdre.get(2);
        }
        break;
      case 1:
        if(joueurParlant.getMonEquipe() != dansLOrdre.get(0).getMonEquipe()){
          adversaire = dansLOrdre.get(0);
        }
        else{
          adversaire = dansLOrdre.get(2);
        }
        break;
      case 2:
      case 3:
        if(joueurParlant.getMonEquipe() != dansLOrdre.get(0).getMonEquipe()){
          adversaire = dansLOrdre.get(0);
        }
        else{
          adversaire = dansLOrdre.get(1);
        }
        break;
    }
    return adversaire;
  }



  public Iterable<Joueur> dansLOrdre() {
    return dansLOrdre;
  }

  public Participants retirer(Joueur joueur) {
    var joueurs = new ArrayList<>(dansLOrdre);
    joueurs.remove(joueur);
    return new Participants(joueurs);
  }
}
