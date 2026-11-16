package megaman.state;

import megaman.model.Inimigo;
import megaman.core.MegaMan;
import megaman.model.TipoArma;

import java.util.List;

public abstract class EstadoBase implements EstadoMegaMan {

    protected void acaoInvalida(String acao) {
        System.out.println("Mega Man nao pode " + acao + " no estado " + getNome() + ".");
    }

    @Override
    public void andar(MegaMan m) {
        acaoInvalida("andar");
    }

    @Override
    public void pular(MegaMan m) {
        acaoInvalida("pular");
    }

    @Override
    public void pularComRush(MegaMan m) {
        acaoInvalida("usar o Rush Coil");
    }

    @Override
    public void deslizar(MegaMan m) {
        acaoInvalida("deslizar");
    }

    @Override
    public void parar(MegaMan m) {
        acaoInvalida("parar");
    }

    @Override
    public void atacar(MegaMan m, List<Inimigo> alvos) {
        m.getArmaAtual().atacar(alvos);
    }

    @Override
    public void receberDano(MegaMan m, int dano) {
        if (dano <= 0 || m.bloquearDanoComEscudo()) {
            return;
        }
        m.perderVida(dano);
        System.out.println("Mega Man sofreu " + dano + " de dano (" + m.getVida() + "/" + MegaMan.VIDA_MAXIMA + ").");
        if (m.estaVivo()) {
            m.setEstado(new Machucado());
        } else {
            System.out.println("Mega Man foi derrotado... GAME OVER.");
            m.setEstado(new Morto());
        }
    }

    @Override
    public void mudarArma(MegaMan m, TipoArma tipo) {
        m.selecionarArma(tipo);
    }

    @Override
    public void curar(MegaMan m, int quantidade) {
        m.aplicarCura(quantidade);
    }

    @Override
    public void reviver(MegaMan m) {
        System.out.println("Mega Man ainda esta vivo.");
    }

    @Override
    public boolean carregarArma(MegaMan m) {
        return m.getArmaAtual().carregar();
    }

    @Override
    public boolean ativarDefesa(MegaMan m) {
        return m.executarDefesaDaArma();
    }

    @Override
    public void atualizar(MegaMan m) {
    }
}
