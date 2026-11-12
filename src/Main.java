import java.util.Scanner;

//cachorrosingle

class Rush {
    private static Rush instancia;

    private Rush() {
    }

    public static Rush getInstance() {
        if (instancia == null) {
            instancia = new Rush();
        }
        return instancia;
    }

    public static boolean existe() {
        return instancia != null;
    }

    public static void remover() {
        instancia = null;
    }

    public void pular() {
        System.out.println("Rush Coil: Mega Man pula bem alto!");
    }
}

//formaState

interface Forma {
    String getNome();
    void atacar();
}

class MegaBuster implements Forma {
    public String getNome() {
        return "Mega Buster";
    }

    public void atacar() {
        System.out.println("Mega Buster: tiro carregado disparado!");
    }
}

class MetalBlade implements Forma {
    public String getNome() {
        return "Metal Blade";
    }

    public void atacar() {
        System.out.println("Metal Blade: laminas serrilhadas lancadas em todas as direcoes!");
    }
}

class LeafShield implements Forma {
    public String getNome() {
        return "Leaf Shield";
    }

    public void atacar() {
        System.out.println("Leaf Shield: escudo de folhas girando ao redor do Mega Man!");
    }
}

class CrashBomber implements Forma {
    public String getNome() {
        return "Crash Bomber";
    }

    public void atacar() {
        System.out.println("Crash Bomber: bomba grudada na parede explodindo!");
    }
}

class AtomicFire implements Forma {
    public String getNome() {
        return "Atomic Fire";
    }

    public void atacar() {
        System.out.println("Atomic Fire: rajada de fogo carregada!");
    }
}

class MegaMan {
    private Forma formaAtual;

    public MegaMan() {
        this.formaAtual = new MegaBuster();
    }

    public void mudarForma(Forma nova) {
        if (formaAtual.getNome().equals(nova.getNome())) {
            System.out.println("A forma " + nova.getNome() + " ja esta selecionada.");
            return;
        }
        formaAtual = nova;
        System.out.println("Forma alterada para " + formaAtual.getNome() + ".");
    }

    public void atacar() {
        formaAtual.atacar();
    }

    public String getFormaAtual() {
        return formaAtual.getNome();
    }
}

//menu

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MegaMan megaMan = new MegaMan();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println();
            System.out.println("===== MEGA MAN =====");
            System.out.println("Forma atual: " + megaMan.getFormaAtual());
            System.out.println("Rush na tela: " + (Rush.existe() ? "sim" : "nao"));
            System.out.println("1 - Spawnar Rush");
            System.out.println("2 - Remover Rush");
            System.out.println("3 - Usar Rush Coil");
            System.out.println("4 - Mudar forma");
            System.out.println("5 - Atacar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    if (Rush.existe()) {
                        System.out.println("So pode existir um Rush na tela por vez.");
                    } else {
                        Rush.getInstance();
                        System.out.println("Rush apareceu na tela!");
                    }
                    break;
                case 2:
                    if (Rush.existe()) {
                        Rush.remover();
                        System.out.println("Rush saiu da tela.");
                    } else {
                        System.out.println("Nao ha nenhum Rush na tela.");
                    }
                    break;
                case 3:
                    if (Rush.existe()) {
                        Rush.getInstance().pular();
                    } else {
                        System.out.println("Spawne o Rush primeiro.");
                    }
                    break;
                case 4:
                    menuFormas(sc, megaMan);
                    break;
                case 5:
                    megaMan.atacar();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
        sc.close();
    }

    private static void menuFormas(Scanner sc, MegaMan megaMan) {
        System.out.println("--- Formas ---");
        System.out.println("1 - Mega Buster");
        System.out.println("2 - Metal Blade");
        System.out.println("3 - Leaf Shield");
        System.out.println("4 - Crash Bomber");
        System.out.println("5 - Atomic Fire");
        System.out.print("Escolha: ");

        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            escolha = -1;
        }

        switch (escolha) {
            case 1:
                megaMan.mudarForma(new MegaBuster());
                break;
            case 2:
                megaMan.mudarForma(new MetalBlade());
                break;
            case 3:
                megaMan.mudarForma(new LeafShield());
                break;
            case 4:
                megaMan.mudarForma(new CrashBomber());
                break;
            case 5:
                megaMan.mudarForma(new AtomicFire());
                break;
            default:
                System.out.println("Forma invalida.");
        }
    }
}