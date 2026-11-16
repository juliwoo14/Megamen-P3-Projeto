package megaman.state;

import megaman.core.MegaMan;

public class Parado extends EstadoBase {
    @Override
    public String getNome() {
        return "Parado";
    }

    @Override
    public void andar(MegaMan m) {
        System.out.println("Mega Man comeca a andar.");
        m.setEstado(new Andando());
    }

    @Override
    public void pular(MegaMan m) {
        System.out.println("Mega Man pula!");
        m.setEstado(new Pulando(1));
    }

    @Override
    public void pularComRush(MegaMan m) {
        System.out.println("Rush Coil: Mega Man pula bem alto!");
        m.setEstado(new Pulando(2));
    }

    @Override
    public void deslizar(MegaMan m) {
        System.out.println("Mega Man desliza pelo chao.");
        m.setEstado(new Deslizando());
    }

    @Override
    public void parar(MegaMan m) {
        System.out.println("Mega Man ja esta parado.");
    }
}
