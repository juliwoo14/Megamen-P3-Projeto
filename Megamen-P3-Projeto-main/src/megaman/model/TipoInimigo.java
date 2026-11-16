package megaman.model;

public enum TipoInimigo {
    MET("Met"),
    SNIPER_JOE("Sniper Joe"),
    AIR_MAN("Air Man"),
    WOOD_MAN("Wood Man");

    private final String nome;

    TipoInimigo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
