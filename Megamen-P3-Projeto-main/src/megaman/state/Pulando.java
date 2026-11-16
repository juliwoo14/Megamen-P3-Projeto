package megaman.state;

import megaman.core.MegaMan;

public class Pulando extends EstadoBase {
    private int turnosNoAr;

    public Pulando(int turnosNoAr) {
        this.turnosNoAr = turnosNoAr;
    }

    @Override
    public String getNome() {
        return "Pulando";
    }

    @Override
    public void andar(MegaMan m) {
        System.out.println("Mega Man se move no ar.");
    }

    @Override
    public void atualizar(MegaMan m) {
        turnosNoAr--;
        if (turnosNoAr <= 0) {
            System.out.println("Mega Man pousou.");
            m.setEstado(new Parado());
        } else {
            System.out.println("Mega Man ainda esta no ar...");
        }
    }
}
