package megaman.factory;

import megaman.model.Inimigo;
import megaman.strategy.Arma;


public interface FaseFactory {
    String getNomeFase();

    Inimigo criarInimigoComum();

    Inimigo criarChefe();

    Arma criarArmaBonus();
}
