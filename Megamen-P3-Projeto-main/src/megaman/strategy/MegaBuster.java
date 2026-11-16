package megaman.strategy;

import megaman.model.Inimigo;
import megaman.model.TipoArma;

public class MegaBuster extends ArmaBase {
    public MegaBuster() {
        super(TipoArma.MEGA_BUSTER, 2, 0);
    }

    @Override
    protected int calcularDano(Inimigo alvo, int indiceDoAlvo) {
        return getDano();
    }

    @Override
    protected String descreverEfeito() {
        return "tiro de energia disparado contra o primeiro alvo!";
    }
}
