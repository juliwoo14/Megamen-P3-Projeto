package megaman.strategy;

import megaman.model.Inimigo;
import megaman.model.TipoArma;

public class AtomicFire extends ArmaBase {
    private static final int CARGA_MINIMA = 2;
    private static final int CARGA_MAXIMA = 3;
    private int nivelCarga;

    public AtomicFire() {
        super(TipoArma.ATOMIC_FIRE, 6, 4);
    }

    @Override
    public boolean carregar() {
        if (nivelCarga >= CARGA_MAXIMA) {
            System.out.println("Atomic Fire ja esta na carga maxima (3/3).");
            return false;
        }
        nivelCarga++;
        System.out.println("Atomic Fire carregando: " + nivelCarga + "/" + CARGA_MAXIMA + ".");
        return true;
    }

    @Override
    protected boolean podeExecutarAtaque() {
        if (nivelCarga < CARGA_MINIMA) {
            System.out.println("Atomic Fire precisa de pelo menos 2 turnos de carga (atual: "
                    + nivelCarga + "/3).");
            return false;
        }
        return true;
    }

    @Override
    protected int calcularDano(Inimigo alvo, int indiceDoAlvo) {
        return getDano() * nivelCarga;
    }

    @Override
    protected void aposAtaque() {
        nivelCarga = 0;
    }

    @Override
    public String getStatusEspecial() {
        return "carga " + nivelCarga + "/" + CARGA_MAXIMA;
    }

    @Override
    protected String descreverEfeito() {
        return "rajada de fogo liberada no nível de carga " + nivelCarga + "!";
    }
}
