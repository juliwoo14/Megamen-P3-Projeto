package megaman.strategy;

import megaman.model.Inimigo;
import megaman.model.TipoArma;

import java.util.List;

public class MetalBlade extends ArmaBase {
    public MetalBlade() {
        super(TipoArma.METAL_BLADE, 4, 2);
    }

    @Override
    protected int calcularDano(Inimigo alvo, int indiceDoAlvo) {
        return getDano();
    }

    @Override
    protected List<Inimigo> selecionarAlvos(List<Inimigo> inimigosVivos) {
        return inimigosVivos;
    }

    @Override
    protected String descreverEfeito() {
        return "lâminas serrilhadas lançadas em várias direções, atingindo todos os inimigos!";
    }
}
