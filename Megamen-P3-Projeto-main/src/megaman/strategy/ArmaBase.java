package megaman.strategy;

import megaman.model.Inimigo;
import megaman.model.TipoArma;

import java.util.ArrayList;
import java.util.List;


public abstract class ArmaBase implements Arma {
    public static final int ENERGIA_MAXIMA = 28;
    private static final int MULTIPLICADOR_FRAQUEZA = 2;

    private final TipoArma tipo;
    private final int dano;
    private final int custo;
    private int energia = ENERGIA_MAXIMA;

    protected ArmaBase(TipoArma tipo, int dano, int custo) {
        this.tipo = tipo;
        this.dano = dano;
        this.custo = custo;
    }

    protected abstract int calcularDano(Inimigo alvo, int indiceDoAlvo);

    protected abstract String descreverEfeito();

    protected List<Inimigo> selecionarAlvos(List<Inimigo> inimigosVivos) {
        return inimigosVivos.isEmpty() ? List.of() : List.of(inimigosVivos.get(0));
    }

    protected boolean podeExecutarAtaque() {
        return true;
    }

    protected void aposAtaque() {
    }

    protected int aplicarEfeito(Inimigo alvo, int danoCalculado) {
        int danoFinal = danoCalculado;
        if (alvo.getFraqueza() == tipo) {
            danoFinal *= MULTIPLICADOR_FRAQUEZA;
            System.out.println("Ponto fraco! Dano dobrado!");
        }
        alvo.receberDano(danoFinal);
        return danoFinal;
    }

    @Override
    public final int atacar(List<Inimigo> alvos) {
        List<Inimigo> vivos = new ArrayList<>();
        if (alvos != null) {
            for (Inimigo alvo : alvos) {
                if (alvo != null && alvo.estaVivo()) {
                    vivos.add(alvo);
                }
            }
        }

        if (vivos.isEmpty()) {
            System.out.println("(nenhum alvo vivo para atingir)");
            return 0;
        }
        if (!temEnergia()) {
            System.out.println(getNome() + ": sem energia! Troque de arma.");
            return 0;
        }
        if (!podeExecutarAtaque()) {
            return 0;
        }

        List<Inimigo> selecionados = selecionarAlvos(vivos);
        if (selecionados.isEmpty()) {
            System.out.println("(nenhum alvo selecionado)");
            return 0;
        }

        gastarEnergia(custo);
        System.out.println(getNome() + ": " + descreverEfeito());
        int danoTotal = 0;
        for (int i = 0; i < selecionados.size(); i++) {
            Inimigo alvo = selecionados.get(i);
            if (alvo != null && alvo.estaVivo()) {
                int danoCalculado = Math.max(0, calcularDano(alvo, i));
                danoTotal += aplicarEfeito(alvo, danoCalculado);
            }
        }
        aposAtaque();
        return danoTotal;
    }

    protected final boolean gastarEnergia(int quantidade) {
        if (quantidade < 0 || energia < quantidade) {
            return false;
        }
        energia -= quantidade;
        return true;
    }

    @Override
    public boolean carregar() {
        System.out.println(getNome() + " nao possui mecanismo de carregamento.");
        return false;
    }

    @Override
    public boolean ativarDefesa() {
        System.out.println(getNome() + " nao possui habilidade defensiva.");
        return false;
    }

    @Override
    public String getStatusEspecial() {
        return "";
    }

    @Override
    public TipoArma getTipo() {
        return tipo;
    }

    @Override
    public String getNome() {
        return tipo.getNome();
    }

    @Override
    public int getDano() {
        return dano;
    }

    @Override
    public int getCusto() {
        return custo;
    }

    @Override
    public int getEnergia() {
        return energia;
    }

    @Override
    public boolean temEnergia() {
        return energia >= custo;
    }

    @Override
    public void recarregar(int quantidade) {
        energia = Math.min(ENERGIA_MAXIMA, energia + Math.max(0, quantidade));
    }
}
