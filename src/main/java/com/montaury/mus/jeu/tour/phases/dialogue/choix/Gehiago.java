package com.montaury.mus.jeu.tour.phases.dialogue.choix;

import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;

public class Gehiago extends Mise {
  public Gehiago() {
    this(1);
  }

  public Gehiago(int mise) {
    super(TypeChoix.GEHIAGO, mise);
  }

  public Dialogue.Deroulement influerSur(Dialogue.Deroulement deroulement) {
    return deroulement.basculerSurAdversaire(prochainsChoixPossibles(), this);
  }

}
