package megaman.strategy;

import megaman.model.Inimigo;
import megaman.model.TipoArma;

public class LeafShield extends ArmaBase {
    public LeafShield() {
        super(TipoArma.LEAF_SHIELD, 4, 3);
    }

    @Override
    protected int calcularDano(Inimigo alvo, int indiceDoAlvo) {
        return getDano();
    }

    @Override
    public boolean ativarDefesa() {
        if (!gastarEnergia(getCusto())) {
            System.out.println("Leaf Shield sem energia suficiente para ativar a defesa.");
            return false;
        }
        System.out.println("Leaf Shield ativado! O proximo dano sera completamente bloqueado.");
        return true;
    }

    @Override
    protected String descreverEfeito() {
        return "folhas cortantes giram ao redor do Mega Man e atingem o primeiro alvo!";
    }
}
