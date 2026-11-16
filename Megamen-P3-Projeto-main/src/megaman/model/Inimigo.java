package megaman.model;


public class Inimigo {
    private final TipoInimigo tipo;
    private final int vidaMaxima;
    private final int dano;
    private final TipoArma fraqueza;
    private int vida;

    public Inimigo(TipoInimigo tipo, int vidaMaxima, int dano, TipoArma fraqueza) {
        if (tipo == null || fraqueza == null || vidaMaxima <= 0 || dano < 0) {
            throw new IllegalArgumentException("Dados invalidos para criar um inimigo.");
        }
        this.tipo = tipo;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.dano = dano;
        this.fraqueza = fraqueza;
    }

    public String getNome() {
        return tipo.getNome();
    }

    public TipoInimigo getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getDano() {
        return dano;
    }

    public TipoArma getFraqueza() {
        return fraqueza;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void receberDano(int quantidade) {
        if (quantidade <= 0 || !estaVivo()) {
            return;
        }
        vida = Math.max(0, vida - quantidade);
        System.out.println(getNome() + " sofreu " + quantidade + " de dano (" + vida + "/" + vidaMaxima + ").");
        if (!estaVivo()) {
            System.out.println(getNome() + " foi derrotado!");
        }
    }

}
