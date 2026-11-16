package megaman.strategy;

import megaman.model.Inimigo;
import megaman.model.TipoArma;

import java.util.List;

public class CrashBomber extends ArmaBase {
    public CrashBomber() {
        super(TipoArma.CRASH_BOMBER, 5, 3);
    }

    @Override
    protected int calcularDano(Inimigo alvo, int indiceDoAlvo) {
        return indiceDoAlvo == 0 ? getDano() : Math.max(1, getDano() / 2);
    }

    @Override
    protected List<Inimigo> selecionarAlvos(List<Inimigo> inimigosVivos) {
        return inimigosVivos;
    }

    @Override
    protected String descreverEfeito() {
        return "bomba explode no alvo principal e causa dano em área aos inimigos próximos!";
    }
}
