package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Opposants {
  private Equipe equipeEsku;
  private Equipe equipeZaku;
  private Deque<Joueur> ordreDePassage;

  public Opposants(Equipe equipeEsku, Equipe equipeZaku) {
    this.equipeEsku = equipeEsku;
    this.equipeZaku = equipeZaku;
    this.ordreDePassage = new ArrayDeque<>();
    for(int i = 0; i < equipeEsku().getListeDesJoueurs().size(); i++)
    {
      ordreDePassage.add(equipeEsku.getListeDesJoueurs().get(i));
      ordreDePassage.add(equipeZaku.getListeDesJoueurs().get(i));
    }
  }

  public void tourner() {
    var tmp = equipeEsku;
    equipeEsku = equipeZaku;
    equipeZaku = tmp;

    for(int i = 0; i < equipeEsku().getListeDesJoueurs().size(); i++)
    {
      equipeEsku.getListeDesJoueurs().get(i).setMonEquipe(equipeEsku);
      equipeZaku.getListeDesJoueurs().get(i).setMonEquipe(equipeZaku);
    }

    Joueur joueurQuiDevientZaku = this.ordreDePassage.removeFirst();
    this.ordreDePassage.add(joueurQuiDevientZaku);

    System.out.println("Ordre joueurs");
    for(Joueur joueur : ordreDePassage){
      System.out.println(joueur.nom());
    }
  }

  public Equipe equipeEsku() {
    return this.equipeEsku;
  }

  public Equipe equipeZaku() {
    return this.equipeZaku;
  }

  public Deque<Joueur> getOrdreDePassage() {
    return ordreDePassage;
  }

  public List<Equipe> dansLOrdre() {
    return List.of(equipeEsku, equipeZaku);
  }
}
