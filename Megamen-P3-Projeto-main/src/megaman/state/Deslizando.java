package megaman.state;

import megaman.core.MegaMan;
import megaman.model.Inimigo;

import java.util.List;

public class Deslizando extends EstadoBase {
    @Override
    public String getNome() {
        return "Deslizando";
    }

    @Override
    public void atacar(MegaMan m, List<Inimigo> alvos) {
        acaoInvalida("atacar");
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
    public void atualizar(MegaMan m) {
        System.out.println("Mega Man terminou o deslize.");
        m.setEstado(new Parado());
    }
}
