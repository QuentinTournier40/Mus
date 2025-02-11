package com.montaury.mus.jeu;

import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MancheTest {

  @BeforeEach
  void setUp() {
    manche = new Manche(mock(Evenements.class));
  }

  @Test
  void devrait_terminer_la_manche_si_hordago_au_grand() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Hordago());
    var joueurZaku = unJoueurFaisantChoix(new Kanta());

    var equipeEsku = new Equipe(joueurEsku, "equipe1");
    var equipeZaku = new Equipe(joueurZaku, "equipe2");

    var resultat = manche.jouer(new Opposants(equipeEsku, equipeZaku));

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_terminer_la_manche_si_un_joueur_a_40_points() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Imido(), new Gehiago(2));
    var joueurZaku = unJoueurFaisantChoix(new Gehiago(40), new Tira());

    var equipeEsku = new Equipe(joueurEsku, "equipe1");
    var equipeZaku = new Equipe(joueurZaku, "equipe2");

    var resultat = manche.jouer(new Opposants(equipeEsku, equipeZaku));

    assertThat(resultat.vainqueur()).isEqualTo(joueurEsku.getMonEquipe());
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_changer_l_ordre_des_opposants_a_la_fin_du_tour() {
    var joueurEsku = unJoueurFaisantChoix(new Mintza(), new Hordago());
    var joueurZaku = unJoueurFaisantChoix(new Kanta());

    var equipeEsku = new Equipe(joueurEsku, "equipe1");
    var equipeZaku = new Equipe(joueurZaku, "equipe2");

    var opposants = new Opposants(equipeEsku, equipeZaku);

    manche.jouer(opposants);

    assertThat(opposants.dansLOrdre()).containsExactly(joueurZaku.getMonEquipe(), joueurEsku.getMonEquipe());
  }

  private Manche manche;
}
