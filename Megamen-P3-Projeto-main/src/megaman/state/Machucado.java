package megaman.state;

import megaman.model.Inimigo;
import megaman.core.MegaMan;

import java.util.List;

public class Machucado extends EstadoBase {
    private int turnosRestantes = 1;

    @Override
    public String getNome() {
        return "Machucado";
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
    public void receberDano(MegaMan m, int dano) {
        System.out.println("Mega Man esta invulneravel e ignora o dano.");
    }

    @Override
    public void atualizar(MegaMan m) {
        turnosRestantes--;
        if (turnosRestantes <= 0) {
            System.out.println("Mega Man se recuperou do golpe.");
            m.setEstado(new Parado());
        } else {
            System.out.println("Mega Man ainda esta atordoado...");
        }
    }
}
