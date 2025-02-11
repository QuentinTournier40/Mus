package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.carte.Paquet;
import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;

import java.util.ArrayList;
import java.util.List;

import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.MINTZA;

public class Mus {
  private static final List<TypeChoix> CHOIX_POSSIBLES_JOUEUR = List.of(TypeChoix.MUS, MINTZA);

  private final Paquet paquet;
  private final Defausse defausse;
  private final Evenements evenements;

  public Mus(Paquet paquet, Defausse defausse, Evenements evenements) {
    this.paquet = paquet;
    this.defausse = defausse;
    this.evenements = evenements;
  }

  public void jouer(Opposants opposants) {
    List<Joueur> lesJoueurs = new ArrayList<>();
    var equipesDansLOrdre = opposants.dansLOrdre();
    for(Equipe equipe : equipesDansLOrdre){
      for(Joueur joueur : equipe.getListeDesJoueurs()){
        joueur.main().jeterTout();
        lesJoueurs.add(joueur);
      }
    }
    while(true){
      lesJoueurs.forEach(this::completerMain);
      if(!veulentAllerMus(lesJoueurs)){
        break;
      }
      lesJoueurs.forEach(this::allerMus);
    }
  }

  private void completerMain(Joueur joueur) {
    joueur.donnerCartes(paquet.tirer(joueur.main().nombreCartesManquantes()));
    evenements.nouvelleMain(joueur);
  }

  private boolean veulentAllerMus(List<Joueur> joueurs) {
    for (Joueur joueur : joueurs) {
      var choix = joueur.interfaceJoueur.faireChoixParmi(CHOIX_POSSIBLES_JOUEUR);
      evenements.choixFait(joueur, choix);
      if (choix.est(MINTZA)) {
        return false;
      }
    }
    return true;
  }

  private void allerMus(Joueur joueur) {
    var cartesAJeter = joueur.interfaceJoueur.cartesAJeter();
    joueur.main().retirer(cartesAJeter);
    defausse.jeter(cartesAJeter);
  }
}
