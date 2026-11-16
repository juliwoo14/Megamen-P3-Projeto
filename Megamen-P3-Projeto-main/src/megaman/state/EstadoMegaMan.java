package megaman.state;

import megaman.model.Inimigo;
import megaman.core.MegaMan;
import megaman.model.TipoArma;

import java.util.List;

public interface EstadoMegaMan {
    String getNome();

    void andar(MegaMan m);

    void pular(MegaMan m);

    void pularComRush(MegaMan m);

    void deslizar(MegaMan m);

    void parar(MegaMan m);

    void atacar(MegaMan m, List<Inimigo> alvos);

    void receberDano(MegaMan m, int dano);

    void mudarArma(MegaMan m, TipoArma tipo);

    void curar(MegaMan m, int quantidade);

    void reviver(MegaMan m);

    boolean carregarArma(MegaMan m);

    boolean ativarDefesa(MegaMan m);

    void atualizar(MegaMan m);
}
