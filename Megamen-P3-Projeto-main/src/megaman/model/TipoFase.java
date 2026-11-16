package megaman.model;

public enum TipoFase {
    WOOD_MAN("Wood Man"),
    AIR_MAN("Air Man");

    private final String nome;

    TipoFase(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
