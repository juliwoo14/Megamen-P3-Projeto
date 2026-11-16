package megaman.factory;

import megaman.model.Inimigo;
import megaman.model.TipoArma;
import megaman.model.TipoInimigo;
import megaman.strategy.Arma;
import megaman.strategy.LeafShield;

public class WoodManFaseFactory implements FaseFactory {
    @Override
    public String getNomeFase() {
        return "Wood Man";
    }

    @Override
    public Inimigo criarInimigoComum() {
        return new Inimigo(TipoInimigo.MET, 8, 2, TipoArma.METAL_BLADE);
    }

    @Override
    public Inimigo criarChefe() {
        return new Inimigo(TipoInimigo.WOOD_MAN, 28, 6, TipoArma.ATOMIC_FIRE);
    }

    @Override
    public Arma criarArmaBonus() {
        return new LeafShield();
    }
}
