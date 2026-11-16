package megaman.singleton;

import megaman.core.MegaMan;


public final class Rush {
    private static Rush instancia;
    private static final int USOS_MAXIMOS = 3;

    private boolean naTela;
    private int usosRestantes = USOS_MAXIMOS;
    private int turnosDeCooldown;

    private Rush() {
    }

    public static synchronized Rush getInstance() {
        if (instancia == null) {
            instancia = new Rush();
        }
        return instancia;
    }

    public synchronized boolean estaNaTela() {
        return naTela;
    }

    public synchronized int getUsosRestantes() {
        return usosRestantes;
    }

    public synchronized int getTurnosDeCooldown() {
        return turnosDeCooldown;
    }

    public synchronized void invocar() {
        if (naTela) {
            System.out.println("Rush ja esta na tela.");
            return;
        }
        if (usosRestantes == 0) {
            usosRestantes = USOS_MAXIMOS;
        }
        naTela = true;
        System.out.println("Rush apareceu na tela! Usos do Coil: " + usosRestantes + "/" + USOS_MAXIMOS + ".");
    }

    public synchronized void dispensar() {
        if (!naTela) {
            System.out.println("Rush ja esta fora da tela.");
            return;
        }
        naTela = false;
        System.out.println("Rush foi dispensado. A instancia Singleton continua preservada.");
    }

    public synchronized void usarCoil(MegaMan megaMan) {
        if (!naTela) {
            System.out.println("Invoque o Rush antes de usar o Coil.");
            return;
        }
        if (megaMan == null || !megaMan.estaVivo()) {
            System.out.println("Rush Coil nao pode ser usado agora.");
            return;
        }
        if (usosRestantes <= 0) {
            System.out.println("Rush Coil sem usos restantes. Dispense e invoque Rush novamente.");
            return;
        }
        if (turnosDeCooldown > 0) {
            System.out.println("Rush Coil em recarga: " + turnosDeCooldown + " turno(s).");
            return;
        }
        String estadoAnterior = megaMan.getNomeEstado();
        megaMan.pularComRush();
        if (!"Pulando".equals(megaMan.getNomeEstado()) || "Pulando".equals(estadoAnterior)) {
            return;
        }
        usosRestantes--;
        turnosDeCooldown = 1;
        System.out.println("Rush Coil utilizado. Usos restantes: " + usosRestantes + ".");
    }

    public synchronized void atualizar() {
        if (turnosDeCooldown > 0) {
            turnosDeCooldown--;
        }
    }
}
