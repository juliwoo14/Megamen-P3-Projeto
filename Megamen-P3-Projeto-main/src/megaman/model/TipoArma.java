package megaman.model;

public enum TipoArma {
    MEGA_BUSTER("Mega Buster"),
    METAL_BLADE("Metal Blade"),
    LEAF_SHIELD("Leaf Shield"),
    CRASH_BOMBER("Crash Bomber"),
    ATOMIC_FIRE("Atomic Fire");

    private final String nome;

    TipoArma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
