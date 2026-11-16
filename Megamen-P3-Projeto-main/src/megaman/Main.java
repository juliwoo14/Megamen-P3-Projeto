package megaman;

import megaman.core.MegaMan;
import megaman.model.Inimigo;
import megaman.factory.FaseFactory;
import megaman.factory.FaseFactoryProvider;
import megaman.model.TipoArma;
import megaman.model.TipoFase;
import megaman.singleton.Rush;
import megaman.strategy.Arma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int CURA_ETANK = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MegaMan megaMan = new MegaMan();
        List<Inimigo> inimigos = new ArrayList<>();
        Rush rush = Rush.getInstance();
        TipoFase tipoFase = TipoFase.WOOD_MAN;
        FaseFactory faseFactory = FaseFactoryProvider.criar(tipoFase);
        Inimigo chefeDaFase = null;
        boolean bonusDaFaseObtido = false;
        int opcao = -1;

        while (opcao != 0) {
            mostrarStatus(megaMan, inimigos, rush, faseFactory);
            mostrarMenu();
            opcao = lerInt(sc);
            System.out.println();

            switch (opcao) {
                case 1 -> megaMan.andar();
                case 2 -> megaMan.pular();
                case 3 -> megaMan.deslizar();
                case 4 -> megaMan.parar();
                case 5 -> megaMan.atacar(inimigos);
                case 6 -> {
                    TipoArma tipo = escolher(sc, "Armas", TipoArma.values());
                    if (tipo != null) {
                        megaMan.mudarArma(tipo);
                    }
                }
                case 7 -> passarTurno(megaMan, inimigos, rush);
                case 8 -> rush.invocar();
                case 9 -> rush.dispensar();
                case 10 -> rush.usarCoil(megaMan);
                case 11 -> {
                    TipoFase escolhida = escolher(sc, "Fases", TipoFase.values());
                    if (escolhida != null) {
                        tipoFase = escolhida;
                        faseFactory = FaseFactoryProvider.criar(tipoFase);
                        inimigos.clear();
                        chefeDaFase = null;
                        bonusDaFaseObtido = false;
                        System.out.println("Fase selecionada: " + faseFactory.getNomeFase() + ".");
                    }
                }
                case 12 -> {
                    Inimigo novo = faseFactory.criarInimigoComum();
                    inimigos.add(novo);
                    System.out.println(novo.getNome() + " apareceu na fase " + faseFactory.getNomeFase()
                            + "! (fraqueza: " + novo.getFraqueza().getNome() + ")");
                }
                case 13 -> {
                    chefeDaFase = faseFactory.criarChefe();
                    inimigos.add(chefeDaFase);
                    bonusDaFaseObtido = false;
                    System.out.println("CHEFE " + chefeDaFase.getNome() + " apareceu! (fraqueza: "
                            + chefeDaFase.getFraqueza().getNome() + ")");
                }
                case 14 -> {
                    if (chefeDaFase == null) {
                        System.out.println("Gere e derrote o chefe da fase antes de receber o bônus.");
                    } else if (chefeDaFase.estaVivo()) {
                        System.out.println("Derrote " + chefeDaFase.getNome() + " para receber a arma bônus.");
                    } else if (bonusDaFaseObtido) {
                        System.out.println("A arma bônus desta fase ja foi obtida.");
                    } else if (megaMan.obterArmaBonus(faseFactory.criarArmaBonus())) {
                        bonusDaFaseObtido = true;
                    }
                }
                case 15 -> megaMan.ativarDefesaArmaAtual();
                case 16 -> {
                    if (megaMan.carregarArmaAtual()) {
                        passarTurno(megaMan, inimigos, rush);
                    }
                }
                case 17 -> megaMan.curar(CURA_ETANK);
                case 18 -> megaMan.reviver();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao invalida.");
            }
        }
        sc.close();
    }

    private static void passarTurno(MegaMan megaMan, List<Inimigo> inimigos, Rush rush) {
        realizarTurnoDosInimigos(inimigos, megaMan);
        megaMan.atualizar();
        rush.atualizar();
    }

    private static void realizarTurnoDosInimigos(List<Inimigo> inimigos, MegaMan megaMan) {
        boolean algumAtacou = false;
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()) {
                System.out.println(inimigo.getNome() + " ataca o Mega Man!");
                megaMan.receberDano(inimigo.getDano());
                algumAtacou = true;
            }
        }
        if (!algumAtacou) {
            System.out.println("Nenhum inimigo vivo para realizar o turno.");
        }
    }

    private static void mostrarStatus(MegaMan megaMan, List<Inimigo> inimigos, Rush rush,
                                      FaseFactory faseFactory) {
        Arma arma = megaMan.getArmaAtual();
        String energia = arma.getCusto() == 0 ? "ilimitada" : arma.getEnergia() + "/28";
        String especial = arma.getStatusEspecial();

        System.out.println();
        System.out.println("===== MEGA MAN =====");
        System.out.println("Vida: " + megaMan.getVida() + "/" + MegaMan.VIDA_MAXIMA
                + " | Estado: " + megaMan.getNomeEstado());
        System.out.println("Arma: " + arma.getNome() + " (energia: " + energia + ")"
                + (especial.isBlank() ? "" : " | " + especial));
        System.out.println("Leaf Shield defensivo: " + (megaMan.temEscudoFolhas() ? "ativo" : "inativo"));
        System.out.println("Rush: " + (rush.estaNaTela() ? "na tela" : "fora da tela")
                + " | Coil: " + rush.getUsosRestantes() + "/3"
                + " | cooldown: " + rush.getTurnosDeCooldown());
        System.out.println("Fase: " + faseFactory.getNomeFase());
        if (inimigos.isEmpty()) {
            System.out.println("Inimigos: nenhum");
        } else {
            System.out.println("Inimigos:");
            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo inimigo = inimigos.get(i);
                String situacao = inimigo.estaVivo()
                        ? inimigo.getVida() + "/" + inimigo.getVidaMaxima() + " HP"
                        : "derrotado";
                System.out.println("  " + (i + 1) + ". " + inimigo.getNome() + " (" + situacao + ")");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("1 - Andar                  10 - Usar Rush Coil");
        System.out.println("2 - Pular                  11 - Escolher fase");
        System.out.println("3 - Deslizar               12 - Gerar inimigo comum");
        System.out.println("4 - Parar                  13 - Gerar chefe");
        System.out.println("5 - Atacar                 14 - Receber arma bônus (após o chefe)");
        System.out.println("6 - Mudar arma             15 - Ativar defesa da arma atual");
        System.out.println("7 - Aguardar turno         16 - Carregar arma atual");
        System.out.println("8 - Invocar Rush           17 - Usar E-Tank (cura)");
        System.out.println("9 - Dispensar Rush         18 - Reviver");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static int lerInt(Scanner sc) {
        if (!sc.hasNextLine()) {
            return 0;
        }
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static <T extends Enum<T>> T escolher(Scanner sc, String titulo, T[] valores) {
        System.out.println("--- " + titulo + " ---");
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + " - " + nomeDe(valores[i]));
        }
        System.out.print("Escolha: ");
        int escolha = lerInt(sc);
        if (escolha < 1 || escolha > valores.length) {
            System.out.println("Opcao invalida.");
            return null;
        }
        return valores[escolha - 1];
    }

    private static String nomeDe(Enum<?> e) {
        if (e instanceof TipoArma arma) {
            return arma.getNome();
        }
        if (e instanceof TipoFase fase) {
            return fase.getNome();
        }
        return e.name();
    }
}
