package com.montaury.mus.jeu.tour.phases.dialogue;

import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.Participants;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueur;
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
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso());
    Joueur joueurZaku = unJoueurFaisantChoix(new Paso());

    var equipe1 = new Equipe(joueurEsku, "equipe1");
    var equipe2 = new Equipe(joueurZaku, "equipe2");

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueurEsku, joueurZaku)));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }

  @Test
  void est_termine_si_le_dernier_choix_est_tira() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Tira());
    Joueur joueurZaku = unJoueurFaisantChoix(new Imido());

    var equipe1 = new Equipe(joueurEsku, "equipe1");
    var equipe2 = new Equipe(joueurZaku, "equipe2");

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueurEsku, joueurZaku)));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }

  @Test
  void est_termine_si_le_dernier_choix_est_idoki() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Idoki());
    Joueur joueurZaku = unJoueurFaisantChoix(new Imido());

    var equipe1 = new Equipe(joueurEsku, "equipe1");
    var equipe2 = new Equipe(joueurZaku, "equipe2");

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueurEsku, joueurZaku)));

    assertThat(recapitulatif.pointsEngages()).isEqualTo(2);
  }

  @Test
  void est_termine_si_le_dernier_choix_est_kanta() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Kanta());
    Joueur joueurZaku = unJoueurFaisantChoix(new Hordago());

    var equipe1 = new Equipe(joueurEsku, "equipe1");
    var equipe2 = new Equipe(joueurZaku, "equipe2");

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueurEsku, joueurZaku)));

    assertThat(recapitulatif.pointsEngages()).isEqualTo(40);
  }

  @Test
  void engage_un_point_si_les_4_participants_sont_paso() {
    Joueur joueur1 = unJoueurFaisantChoix(new Paso());
    Joueur joueur2 = unJoueurFaisantChoix(new Paso());
    Joueur joueur3 = unJoueurFaisantChoix(new Paso());
    Joueur joueur4 = unJoueurFaisantChoix(new Paso());

    var equipe1 = new Equipe(joueur1, joueur3, "equipe1");
    var equipe2 = new Equipe(joueur2, joueur4, "equipe2");

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueur1, joueur2)));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }


  @Test
  void est_termine_si_le_dernier_choix_est_tira_pour_l_equipe() {
    Joueur joueur1 = unJoueurFaisantChoix(new Paso(), new Tira());
    Joueur joueur2 = unJoueurFaisantChoix(new Imido());
    Joueur joueur3 = unJoueurFaisantChoix(new Tira());
    Joueur joueur4 = unJoueurFaisantChoix(new Paso());

    var equipe1 = new Equipe(joueur1, joueur3, "equipe1");
    var equipe2 = new Equipe(joueur2, joueur4,"equipe2");

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueur1, joueur2, joueur3, joueur4)));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }




  @Test
  void est_termine_si_le_dernier_choix_est_idoki_a_quatre() {
    Joueur joueur1 = unJoueurFaisantChoix(new Paso(), new Idoki());
    Joueur joueur2 = unJoueurFaisantChoix(new Imido());
    Joueur joueur3 = unJoueurFaisantChoix(new Tira());
    Joueur joueur4 = unJoueurFaisantChoix(new Paso());

    var equipe1 = new Equipe(joueur1, joueur3, "equipe1");
    var equipe2 = new Equipe(joueur2, joueur4, "equipe2");

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueur1, joueur2, joueur3, joueur4)));

    assertThat(recapitulatif.pointsEngages()).isEqualTo(2);
  }

  @Test
  void est_termine_si_le_dernier_choix_est_kanta2() {
    Joueur joueur1 = unJoueurFaisantChoix(new Paso(), new Kanta());
    Joueur joueur2 = unJoueurFaisantChoix(new Hordago());
    Joueur joueur3 = unJoueurFaisantChoix(new Paso());
    Joueur joueur4 = unJoueurFaisantChoix(new Imido());

    var equipe1 = new Equipe(joueur1, joueur3, "equipe1");
    var equipe2 = new Equipe(joueur2, joueur4, "equipe2");

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueur1, joueur2)));

    assertThat(recapitulatif.pointsEngages()).isEqualTo(40);
  }

  private Dialogue dialogue;

}
