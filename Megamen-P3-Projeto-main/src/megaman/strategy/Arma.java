package megaman.strategy;

import megaman.model.Inimigo;
import megaman.model.TipoArma;

import java.util.List;

public interface Arma {
    TipoArma getTipo();

    String getNome();

    int getDano();

    int getCusto();

    int getEnergia();

    boolean temEnergia();

    int atacar(List<Inimigo> alvos);

    default int atacar(Inimigo alvo) {
        return atacar(alvo == null ? List.of() : List.of(alvo));
    }

    boolean carregar();

    boolean ativarDefesa();

    String getStatusEspecial();

    void recarregar(int quantidade);
}
