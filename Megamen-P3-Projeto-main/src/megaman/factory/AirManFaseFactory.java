package megaman.factory;

import megaman.model.Inimigo;
import megaman.model.TipoArma;
import megaman.model.TipoInimigo;
import megaman.strategy.Arma;
import megaman.strategy.MetalBlade;

public class AirManFaseFactory implements FaseFactory {
    @Override
    public String getNomeFase() {
        return "Air Man";
    }

    @Override
    public Inimigo criarInimigoComum() {
        return new Inimigo(TipoInimigo.SNIPER_JOE, 14, 4, TipoArma.CRASH_BOMBER);
    }

    @Override
    public Inimigo criarChefe() {
        return new Inimigo(TipoInimigo.AIR_MAN, 28, 6, TipoArma.LEAF_SHIELD);
    }

    @Override
    public Arma criarArmaBonus() {
        return new MetalBlade();
    }
}
