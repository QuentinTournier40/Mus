package com.montaury.mus.jeu.tour.phases.dialogue;

import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.Participants;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DialogueTest {

  @BeforeEach
  void setUp() {
    dialogue = new Dialogue(mock(Evenements.class));
  }

  @Test
  void engage_un_point_si_les_2_participants_sont_paso() {
    Joueur joueur1 = unJoueurFaisantChoix(new Paso());
    Joueur joueur2 = unJoueurFaisantChoix(new Paso());

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueur1, joueur2)));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }

  @Test
  void test2() {
    Joueur joueur1 = unJoueurFaisantChoix(new Imido());
    Joueur joueur2 = unJoueurFaisantChoix(new Tira());
    Joueur joueur3 = unJoueurFaisantChoix(new Tira());
    Joueur joueur4 = unJoueurFaisantChoix(new Paso());

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueur1, joueur2, joueur3, joueur4)));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }


  @Test
  void est_termine_si_le_dernier_choix_est_tira() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Tira());
    Joueur joueurZaku = unJoueurFaisantChoix(new Imido());

    var equipeEsku = new Equipe(joueurEsku);
    var equipeZaku = new Equipe(joueurZaku);

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueurEsku, joueurZaku)));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }

  @Test
  void est_termine_si_le_dernier_choix_est_idoki() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Idoki());
    Joueur joueurZaku = unJoueurFaisantChoix(new Imido());

    var equipeEsku = new Equipe(joueurEsku);
    var equipeZaku = new Equipe(joueurZaku);

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueurEsku, joueurZaku)));

    assertThat(recapitulatif.pointsEngages()).isEqualTo(2);
  }

  @Test
  void est_termine_si_le_dernier_choix_est_kanta() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Kanta());
    Joueur joueurZaku = unJoueurFaisantChoix(new Hordago());

    var equipeEsku = new Equipe(joueurEsku);
    var equipeZaku = new Equipe(joueurZaku);

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueurEsku, joueurZaku)));

    assertThat(recapitulatif.pointsEngages()).isEqualTo(40);
  }
  private Dialogue dialogue;

}
