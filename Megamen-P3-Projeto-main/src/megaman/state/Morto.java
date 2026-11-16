package megaman.state;

import megaman.model.Inimigo;
import megaman.core.MegaMan;
import megaman.model.TipoArma;

import java.util.List;

public class Morto extends EstadoBase {
    @Override
    public String getNome() {
        return "Morto";
    }

    @Override
    public void atacar(MegaMan m, List<Inimigo> alvos) {
        acaoInvalida("atacar");
    }

    @Override
    public void receberDano(MegaMan m, int dano) {
        System.out.println("Mega Man ja foi derrotado.");
    }

    @Override
    public void mudarArma(MegaMan m, TipoArma tipo) {
        acaoInvalida("trocar de arma");
    }

    @Override
    public void curar(MegaMan m, int quantidade) {
        acaoInvalida("usar cura");
    }

    @Override
    public boolean carregarArma(MegaMan m) {
        acaoInvalida("carregar arma");
        return false;
    }

    @Override
    public boolean ativarDefesa(MegaMan m) {
        acaoInvalida("ativar defesa");
        return false;
    }

    @Override
    public void reviver(MegaMan m) {
        m.restaurarCheckpoint();
    }
}
