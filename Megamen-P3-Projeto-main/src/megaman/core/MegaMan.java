package megaman.core;

import megaman.model.Inimigo;
import megaman.model.TipoArma;
import megaman.state.EstadoMegaMan;
import megaman.state.Parado;
import megaman.strategy.Arma;
import megaman.strategy.ArmaBase;
import megaman.strategy.AtomicFire;
import megaman.strategy.CrashBomber;
import megaman.strategy.LeafShield;
import megaman.strategy.MegaBuster;
import megaman.strategy.MetalBlade;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MegaMan {
    public static final int VIDA_MAXIMA = 28;

    private final Map<TipoArma, Arma> arsenal = criarArsenalInicial();
    private Arma armaAtual = arsenal.get(TipoArma.MEGA_BUSTER);
    private EstadoMegaMan estado = new Parado();
    private int vida = VIDA_MAXIMA;
    private boolean escudoFolhasAtivo;

    private static Map<TipoArma, Arma> criarArsenalInicial() {
        Map<TipoArma, Arma> armas = new EnumMap<>(TipoArma.class);
        armas.put(TipoArma.MEGA_BUSTER, new MegaBuster());
        armas.put(TipoArma.METAL_BLADE, new MetalBlade());
        armas.put(TipoArma.LEAF_SHIELD, new LeafShield());
        armas.put(TipoArma.CRASH_BOMBER, new CrashBomber());
        armas.put(TipoArma.ATOMIC_FIRE, new AtomicFire());
        return armas;
    }

    public void andar() {
        estado.andar(this);
    }

    public void pular() {
        estado.pular(this);
    }

    public void pularComRush() {
        estado.pularComRush(this);
    }

    public void deslizar() {
        estado.deslizar(this);
    }

    public void parar() {
        estado.parar(this);
    }

    public void atacar(Inimigo alvo) {
        estado.atacar(this, alvo == null ? List.of() : List.of(alvo));
    }

    public void atacar(List<Inimigo> alvos) {
        estado.atacar(this, alvos == null ? List.of() : alvos);
    }

    public void receberDano(int dano) {
        estado.receberDano(this, dano);
    }

    public void atualizar() {
        estado.atualizar(this);
    }

    public void mudarArma(TipoArma tipo) {
        estado.mudarArma(this, tipo);
    }

    public void selecionarArma(TipoArma tipo) {
        if (tipo == null || !arsenal.containsKey(tipo)) {
            System.out.println("Arma invalida ou ainda nao adquirida.");
            return;
        }
        if (armaAtual.getTipo() == tipo) {
            System.out.println("A arma " + tipo.getNome() + " ja esta selecionada.");
            return;
        }
        armaAtual = arsenal.get(tipo);
        System.out.println("Arma alterada para " + armaAtual.getNome() + ".");
    }

    public Arma getArmaAtual() {
        return armaAtual;
    }

    public boolean carregarArmaAtual() {
        return estado.carregarArma(this);
    }

    public boolean ativarDefesaArmaAtual() {
        return estado.ativarDefesa(this);
    }

    public boolean executarDefesaDaArma() {
        if (escudoFolhasAtivo) {
            System.out.println("O Leaf Shield ja esta ativo.");
            return false;
        }
        if (!armaAtual.ativarDefesa()) {
            return false;
        }
        ativarEscudoFolhas();
        return true;
    }

    public boolean obterArmaBonus(Arma armaBonus) {
        if (armaBonus == null || !estaVivo()) {
            System.out.println("Nao foi possivel obter a arma bonus.");
            return false;
        }
        arsenal.put(armaBonus.getTipo(), armaBonus);
        armaAtual = armaBonus;
        System.out.println("Arma bonus adquirida e equipada: " + armaBonus.getNome() + "!");
        return true;
    }

    public void curar(int quantidade) {
        estado.curar(this, quantidade);
    }

    public void aplicarCura(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("A quantidade de cura deve ser positiva.");
            return;
        }
        int vidaAnterior = vida;
        vida = Math.min(VIDA_MAXIMA, vida + quantidade);
        System.out.println("Vida recuperada: " + vida + "/" + VIDA_MAXIMA
                + " (" + (vida - vidaAnterior) + " pontos recuperados).");
    }

    public void reviver() {
        estado.reviver(this);
    }

    public void restaurarCheckpoint() {
        vida = VIDA_MAXIMA;
        escudoFolhasAtivo = false;
        arsenal.values().forEach(a -> a.recarregar(ArmaBase.ENERGIA_MAXIMA));
        armaAtual = arsenal.get(TipoArma.MEGA_BUSTER);
        estado = new Parado();
        System.out.println("Mega Man voltou ao checkpoint com vida e energia cheias!");
    }

    public void perderVida(int dano) {
        vida = Math.max(0, vida - Math.max(0, dano));
    }

    public int getVida() {
        return vida;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void ativarEscudoFolhas() {
        escudoFolhasAtivo = true;
    }

    public boolean temEscudoFolhas() {
        return escudoFolhasAtivo;
    }

    public boolean bloquearDanoComEscudo() {
        if (!escudoFolhasAtivo) {
            return false;
        }
        escudoFolhasAtivo = false;
        System.out.println("Leaf Shield absorveu completamente o proximo dano!");
        return true;
    }

    public EstadoMegaMan getEstado() {
        return estado;
    }

    public void setEstado(EstadoMegaMan novo) {
        if (novo == null) {
            throw new IllegalArgumentException("O estado do Mega Man nao pode ser nulo.");
        }
        this.estado = novo;
    }

    public String getNomeEstado() {
        return estado.getNome();
    }
}
