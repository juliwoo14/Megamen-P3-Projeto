package megaman.state;

import megaman.core.MegaMan;

public class Andando extends EstadoBase {
    @Override
    public String getNome() {
        return "Andando";
    }

    @Override
    public void andar(MegaMan m) {
        System.out.println("Mega Man ja esta andando.");
    }

    @Override
    public void pular(MegaMan m) {
        System.out.println("Mega Man pula em movimento!");
        m.setEstado(new Pulando(1));
    }

    @Override
    public void pularComRush(MegaMan m) {
        System.out.println("Rush Coil: Mega Man pula bem alto!");
        m.setEstado(new Pulando(2));
    }

    @Override
    public void deslizar(MegaMan m) {
        System.out.println("Mega Man desliza em alta velocidade.");
        m.setEstado(new Deslizando());
    }

    @Override
    public void parar(MegaMan m) {
        System.out.println("Mega Man para de andar.");
        m.setEstado(new Parado());
    }
}
