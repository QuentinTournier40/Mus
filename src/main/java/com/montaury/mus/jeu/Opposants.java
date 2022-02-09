package com.montaury.mus.jeu;

import java.util.List;

public class Opposants {
  private Equipe equipeEsku;
  private Equipe equipeZaku;

  public Opposants(Equipe equipeEsku, Equipe equipeZaku) {
    this.equipeEsku = equipeEsku;
    this.equipeZaku = equipeZaku;
  }

  public void tourner() {
    var tmp = equipeEsku;
    equipeEsku = equipeZaku;
    equipeZaku = tmp;
  }

  public Equipe equipeEsku() {
    return this.equipeEsku;
  }

  public Equipe equipeZaku() {
    return this.equipeZaku;
  }

  public List<Equipe> dansLOrdre() {
    return List.of(equipeEsku, equipeZaku);
  }
}
